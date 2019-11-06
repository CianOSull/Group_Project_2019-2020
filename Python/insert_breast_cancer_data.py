from firebase import firebase
import numpy as np
#mean_radius,
# mean_texture,
# mean_perimeter,
# mean_area,
# mean_smoothness,
# diagnosis
data = np.genfromtxt("Breast_cancer_data.csv", delimiter=',', dtype=None)
marray = data[0]
print(marray)
'''
firebase = firebase.FirebaseApplication('https://medi-screen.firebaseio.com/', None)  # establish connection to database

for i in range(len(data)-1):
    list = data[i]

    dict = {'Mean Texture': list[0], 'Mean Perimeter': list[1], 'Mean Area': list[2], 'Mean Smoothness': list[3], 'Diagnosis': list[4]} # create sample data

    res = firebase.post('/medi-screen/Breast-Cancer', dict) # use the connection to send data

    print(res)
'''