#!/usr/bin/env python3

filepath = "./audio_wav/"     #Input audio file path
output_filepath = "./Transcripts/" #Final transcript path
bucketname = "smartconfbucket" #Name of the bucket created in the step before

# Import libraries
from pydub import AudioSegment
import io
import os
import sys
from google.cloud import speech
from google.cloud.speech import enums
from google.cloud.speech import types
import wave
from google.cloud import storage

def stereo_to_mono(audio_file_name):
    sound = AudioSegment.from_wav(audio_file_name)
    sound = sound.set_channels(1)
    sound.export(audio_file_name, format="wav")

def mp3_to_wav(audio_file_name):
    print(audio_file_name.split('.')[2])
    print(audio_file_name.__contains__(".mp3"))
    if audio_file_name.__contains__(".mp3"):
        print("true")
        sound = AudioSegment.from_mp3(audio_file_name)
        audio_file_name = audio_file_name.split('.')[0] + '.wav'
        print(audio_file_name)
        sound.export(audio_file_name, format="wav")

def frame_rate_channel(audio_file_name):
    with wave.open("audio.wav", "rb") as wave_file:
        frame_rate = wave_file.getframerate()
        channels = wave_file.getnchannels()
    return frame_rate, channels

def upload_blob(bucket_name, source_file_name, destination_blob_name):
    """Uploads a file to the bucket."""
    storage_client = storage.Client()
    bucket = storage_client.get_bucket(bucket_name)
    blob = bucket.blob(destination_blob_name)
    blob.upload_from_filename(source_file_name)

def delete_blob(bucket_name, blob_name):
    """Deletes a blob from the bucket."""
    storage_client = storage.Client()
    bucket = storage_client.get_bucket(bucket_name)
    blob = bucket.blob(blob_name)
    blob.delete()


def google_transcribe(audio_file_name):
    file_name = filepath + audio_file_name
    #mp3_to_wav(file_name)

    # The name of the audio file to transcribe
    file_name = 'audio.wav'
    frame_rate, channels = frame_rate_channel(file_name)

    if channels > 1:
        stereo_to_mono(file_name)

    bucket_name = bucketname
    source_file_name = filepath + audio_file_name
    destination_blob_name = audio_file_name

    upload_blob(bucket_name, source_file_name, destination_blob_name)

    gcs_uri = 'gs://' + bucketname + '/' + audio_file_name
    transcript = ''

    client = speech.SpeechClient()
    audio = types.RecognitionAudio(uri=gcs_uri)

    config = types.RecognitionConfig(
        encoding=enums.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=frame_rate,
        language_code='fr-FR')

    # Detects speech in the audio file
    operation = client.long_running_recognize(config, audio)
    response = operation.result(timeout=10000)

    for result in response.results:
        transcript += result.alternatives[0].transcript

    delete_blob(bucket_name, destination_blob_name)

    return transcript

def write_transcripts(transcript_filename,transcript):
    f = open(output_filepath + transcript_filename,"w+")
    f.write(transcript)
    f.close()

def script_pourri_de_guillaume():
    #sys.path.append('/path/to/ffmpeg')
    for audio_file_name in os.listdir(filepath):
        print(audio_file_name)
        transcript = google_transcribe(audio_file_name)
        print(transcript)
        transcript_filename = 'transcript.txt'
        write_transcripts(transcript_filename, transcript)

