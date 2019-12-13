FROM python:alpine3.7

WORKDIR /usr/src/app

ENV FLASK_APP app.py
COPY requirements.txt ./
RUN apk add g++
RUN pip install --no-cache-dir -r requirements.txt --upgrade https://storage.googleapis.com/tensorflow/mac/cpu/tensorflow-0.12.0-py3-none-any.whl
EXPOSE 80

COPY . .

CMD ["flask", "run", "--host=0.0.0.0", "--port=80"]