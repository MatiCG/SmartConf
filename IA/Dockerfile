FROM python:alpine3.7

WORKDIR /usr/src/app

ENV FLASK_APP app.py
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 80

COPY . .

CMD ["flask", "run", "--host=0.0.0.0", "--port=80"]