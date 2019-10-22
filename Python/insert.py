from firebase import firebase

firebase = firebase.FirebaseApplication('https://medi-screen.firebaseio.com/', None)
data = {'Name': 'David Manilla', 'Email': 'dave.m@gmail.com', 'Addres': '111 Green place, Limerick', 'Age': 25, 'Weight': 80}
res = firebase.post('/medi-screen/Patients', data)
print(res)
