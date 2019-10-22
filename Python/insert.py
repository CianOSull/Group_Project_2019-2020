from firebase import firebase

firebase = firebase.FirebaseApplication('https://medi-screen.firebaseio.com/', None) # establish connection to database

data = {'Name': 'David Manilla', 'Email': 'dave.m@gmail.com', 'Addres': '111 Green place, Limerick', 'Age': 25, 'Weight': 80} # create sample data

res = firebase.post('/medi-screen/Patients', data) # use the connection to send data

print(res)
