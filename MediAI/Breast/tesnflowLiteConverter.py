#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Nov  4 17:03:57 2019

@author: cianosullivan
"""
'''
from tensorflow import lite
converter = lite.TFLiteConverter.from_keras_model_file('model.h5')
tfmodel = converter.convert()
open("model.tflite","wb").write(tfmodel)
'''
import tensorflow as tf

new_model= tf.keras.models.load_model(filepath="breast_model.h5")

tflite_converter = tf.lite.TFLiteConverter.from_keras_model(new_model)
tflite_model = tflite_converter.convert()
open("tf_lite_breast_cancer_model.tflite", "wb").write(tflite_model)