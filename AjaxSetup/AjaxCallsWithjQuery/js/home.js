$(document).ready(function(){

//calling Ajax function
    $.ajax({
		//Ajax call needs a type(endpoint)
        type: 'GET',
		//we need our URL(endpoint)
        url: 'http://localhost:8080/contacts',
		//We need to process the data that is coming back from the server
		//Call it contactArray
        success: function(contactArray) {
            // get a reference to the 'allContacts' div
            var contactsDiv = $('#allContacts');

            // go through each of the returned contacts and append the info to the
            //contactsDiv
			//contractArray is array of contact information
			//for each piece of information in contactArray, run this function hand me index and pull out next contact
            $.each(contactArray, function(index, contact) {
				
                var contactInfo = '<p>';
                contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                contactInfo += 'Company: ' + contact.company + '<br>';
                contactInfo += 'Email: ' + contact.email + '<br>';
                contactInfo += 'Phone: ' + contact.phone + '<br>';
                contactInfo += '<hr>';

                contactsDiv.append(contactInfo);
            })
        },
        error: function() {
            alert('FAILURE!');
        }
    });

	//code run if user clicks button
    $('#add-button').on('click', function() {
		//make Ajax call
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/contact',
			//JSON.stringify take a JSON object
			//take JSON notation and put into JSON String, post that to server, server will 
			//take that and turn into information that will allow to turn into a object
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                company: $('#add-company').val(),
                phone: $('#add-phone').val(),
                email: $('#add-email').val()
            }),
			//HTTP Headers (what is this thing that is coming across)
			//Gives guidance to web service as to what is coming across (it's json data)
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
			//we accept json back
            'dataType': 'json',
            success: function(contact) {
                // get a reference to the 'newContact' div
                var newContactDiv = $('#newContact');

                // append contact info to the newContact div
                var contactInfo = '<p>';
                contactInfo += 'Name: ' + contact.firstName + ' ' + contact.lastName + '<br>';
                contactInfo += 'Company: ' + contact.company + '<br>';
                contactInfo += 'Email: ' + contact.email + '<br>';
                contactInfo += 'Phone: ' + contact.phone + '<br>';
                contactInfo += '<hr>';

                newContactDiv.append(contactInfo);

            },
            error: function() {
                alert('FAILURE');
            }
        });

    });

})
