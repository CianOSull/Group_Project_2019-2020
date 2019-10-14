import logging
from flask import flask

app = Flask(__name__)

@app.route('/')
def test():
    return("Did this work guys?")

if(__name__ == '__main__'):
    app.run(host='127.0.0.1', port=8080, debug=True)