#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Nov  4 12:58:51 2019

@author: cianosullivan
"""

# load and evaluate a saved model
from keras.models import load_model
from numpy import array
from numpy import loadtxt

dataset = loadtxt("heartDataset.csv", delimiter=",")
# split into input (X) and output (Y) variables
X = dataset[:,0:13]
Y = dataset[:,13]

# load model
model = load_model('heart_model.h5')

# summarize model.
#model.summary()

predictions = model.predict_classes(X)
# summarize the first 5 cases
for i in range(1000):
	print('%s => %d (expected %d)' % (X[i].tolist(), predictions[i], Y[i]))

# new instance where we do not know the answer
#Xnew = array([[21475,2,171,69,140,90,1,1,0,0,1]])
# make a prediction
#ynew = model.predict_classes(Xnew)
# show the inputs and predicted outputs
#print("X=%s, Predicted=%s" % (Xnew[0], ynew[0]))
