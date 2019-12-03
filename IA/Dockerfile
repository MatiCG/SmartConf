FROM python:alpine3.7

WORKDIR /usr/src/app

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
EXPOSE 5000

COPY . .

CMD ["flask", "run", "--host=127.0.0.1", "--port=5000"]