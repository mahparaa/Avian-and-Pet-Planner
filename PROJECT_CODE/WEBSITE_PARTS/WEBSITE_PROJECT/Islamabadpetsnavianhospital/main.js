// Initialize Firebase (ADD YOUR OWN DATA)
var config = {
    apiKey: "AIzaSyAhB0U5qreFPQ4XEbmAcnqJ-mNY9waUkIE",
    authDomain: "newproectdb.firebaseapp.com",
    databaseURL: "https://newproectdb.firebaseio.com",
    projectId: "newproectdb",
    storageBucket: "newproectdb.appspot.com",
    messagingSenderId: "782627855252"
  };
  firebase.initializeApp(config);
// Reference messages collection
var messagesRef = firebase.database().ref('Website_Appointment');

// Listen for form submit
document.getElementById('contactForm').addEventListener('submit', submitForm);

// Submit form
function submitForm(e){
  e.preventDefault();

  // Get values
  var name = getInputVal('name');
  var email = getInputVal('email');
  var date = getInputVal('date');
  var time = getInputVal('time');
  var phone = getInputVal('phone');
  var message = getInputVal('message');

  // Save message
  saveMessage(name, email, phone,date,time, message);

  // Show alert
   document.querySelector('.alert').style.display = 'block';

  // Hide alert after 3 seconds
  setTimeout(function()
  {
    document.querySelector('.alert').style.display = 'none';
  },3000);

  // Clear form
  document.getElementById('contactForm').reset();
}

// Function to get get form values
function getInputVal(id){
  return document.getElementById(id).value;
}

// Save message to firebase
function saveMessage(name, email, phone,date,time, message){
  var newMessageRef = messagesRef.push();
  newMessageRef.set({
    name: name,
    email:email,
    date:date,
    time:time,
    phone:phone,
    message:message
  });
}