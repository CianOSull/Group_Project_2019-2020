# -*- coding: utf-8 -*-
"""
Created on Tue Oct 15 12:29:08 2019
@author: Cian
"""

# first neural network with keras tutorial
from numpy import loadtxt
from keras.models import Sequential
from keras.layers import Dense
# load the dataset
dataset = loadtxt('heartDataset.csv', delimiter=',')
# split into input (X) and output (y) variables
X = dataset[:,0:13]
y = dataset[:,13]
# define the keras model
model = Sequential()
model.add(Dense(12, input_dim=13, activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(1, activation='sigmoid'))
# compile the keras model
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
# fit the keras model on the dataset
model.fit(X, y, epochs=150, batch_size=80)
# evaluate the keras model
_, accuracy = model.evaluate(X, y)
print('Accuracy: %.2f' % (accuracy*100))
model.save("heart_model.h5")
print("Saved model to disk")