from firebase import firebase

firebase = firebase.FirebaseApplication('https://medi-screen.firebaseio.com/', None) # establish connection to database

data = {'Name': 'David Batista', 'Email': 'b.dave@gmail.com', 'Address': '134 Green place, Limerick', 'Age': 45, 'Weight': 120} # create sample data

res = firebase.post('/medi-screen/Patients', data) # use the connection to send data

print(res)