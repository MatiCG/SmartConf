filepath = "./audio_wav/"     #Input audio file path
output_filepath = "./Transcripts/" #Final transcript path
bucketname = "detruitpascebucket" #Name of the bucket created in the step before

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
import firebase_admin
from firebase_admin import credentials

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
    with wave.open(audio_file_name, "rb") as wave_file:
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


def upload_to_clood(audio_file_name):
    file_name = filepath + audio_file_name
    #mp3_to_wav(file_name)

    # The name of the audio file to transcribe
    file_name = audio_file_name
    frame_rate, channels = frame_rate_channel(file_name)

    if channels > 1:
        stereo_to_mono(file_name)

    bucket_name = bucketname
    source_file_name = audio_file_name
    destination_blob_name = audio_file_name
    upload_blob(bucket_name, source_file_name, destination_blob_name)

def google_transcribe(file_name):
    gcs_uri = 'gs://smartconf-eip-epitech.appspot.com/Meetings/' + file_name
    transcript = ''
    client = speech.SpeechClient()
    audio = types.RecognitionAudio(uri=gcs_uri)
    #frame_rate, channels = frame_rate_channel(file_name)

    config = types.RecognitionConfig(
        encoding=enums.RecognitionConfig.AudioEncoding.LINEAR16,
        sample_rate_hertz=48000,
        language_code='fr-FR')

    # Detects speech in the audio file
    operation = client.long_running_recognize(config, audio)
    response = operation.result(timeout=10000)

    for result in response.results:
        transcript += result.alternatives[0].transcript

    #delete_blob(bucketname, file_name)

    return transcript

def write_transcripts(transcript_filename, transcript):
    f = open(output_filepath + transcript_filename, "w+")
    f.write(transcript)
    f.close()

def script_pourri_de_guillaume(file_name):
    cred = credentials.Certificate("./fire.json")
    firebase_admin.initialize_app(cred)
    transcript = google_transcribe(file_name)
    return transcript


#if __name__ == "__main__":
  #  #upload_to_clood("audio.wav")
    #tr = script_pourri_de_guillaume("audio.wav")
    #print(tr)

