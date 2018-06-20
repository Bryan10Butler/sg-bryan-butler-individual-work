$(document).ready(function () {
	
	loadContacts();
	
	//click handler (we could do "on" as well)
	$('#add-button').click(function (event){
		
		
		var haveValidationErrors = checkAndDisplayValidationErrors($('#add-form').find('input'));
		
		if(haveValidationErrors) {
			return false;
		}
		
		
		//make ajax call to server
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/contact',
			//send data as JSON string
			data: JSON.stringify({
				//grab element on form//grab value of it
				firstName: $('#add-first-name').val(),
				lastName: $('#add-last-name').val(),
				company: $('#add-company').val(),
				phone: $('#add-phone').val(),
				email: $('#add-email').val()
			}),
			//information about data
			headers: {
				//accept JSON format
				'Accept': 'application/json',
				//content type JSON
				'Content-Type': 'application/json'
			},
			//our data type
			'dataType': 'json',
			//what action to take upon success
			success: function() {
				//empty error messages before saying success
				$('#errorMessages').empty();
				$('#add-first-name').val('');
				$('#add-last-name').val('');
				$('#add-company').val('');
				$('#add-phone').val('');
				$('#add-email').val('');
				//call load contacts to see new contact loaded in the table
				loadContacts();
			},
			error: function() {
				$('#errorMessages')
				//append a list item onto our ul
				.append($('<li>')
				//turn red
				.attr({class: 'list-group-item list-group-item-danger'})
				//error message associated
				.text('Error calling web service. Please try again later'));
			}
		})
	});
	
	//add click handler
	$('#edit-button').click(function(event) {
		
		
		var haveValidationErrors = checkAndDisplayValidationErrors($('#edit-form').find('input'));
		
		if(haveValidationErrors) {
			return false;
		}
		
		
		//make ajax call
		$.ajax({
			type: 'PUT',
			url: 'http://localhost:8080/contact/' + $('#edit-contact-id').val(),
			//grab data out of edit form and send to server
			data: JSON.stringify({
				contactId: $('#edit-contact-id').val(),
				firstName: $('#edit-first-name').val(),
				lastName: $('#edit-last-name').val(),
				company: $('#edit-company').val(),
				phone: $('#edit-phone').val(),
				email: $('#edit-email').val()
			}),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			//we expect JSON back
			'dataType': 'json',
			//on success, do an action
			success: function() {
				//refresh the form
				$('#errorMessages').empty();
				hideEditForm();
				loadContacts();
				
				
			},
			error: function() {
				$('#errorMessages')
				//append a list item onto our ul
				.append($('<li>')
				//turn red
				.attr({class: 'list-group-item list-group-item-danger'})
				//error message associated
				.text('Error calling web service. Please try again later'));
			}
			
		})
	});	
});
function loadContacts() {
	//call the clearContactTable function
	clearContactTable();
	//grab element from table body to append rows
	var contentRows = $('#contentRows');
	//make ajax call to our web service
	$.ajax({
		//get call to API
		type: 'GET',
		//where
		url: 'http://localhost:8080/contacts',
		//contactArray passed into our function
		success: function(contactArray) {
			//on success give us the following
			//grabbing contact at index for every contactArray
			$.each(contactArray, function(index, contact) {
				//variables
				var name = contact.firstName + ' ' + contact.lastName;
				var company = contact.company;
				var contactId = contact.contactId;
				//table row variable
				var row = '<tr>';
					row += '<td>' + name + '</td>';
					row += '<td>' + company + '</td>';
					//on click call the showEditFunction
					row += '<td><a onclick="showEditForm(' + contactId + ')">Edit</a></td>';
					row += '<td><a onclick="deleteContact(' + contactId + ')">Delete</a></td>';
					row += '</tr>';
				//append data
				contentRows.append(row);	
			});
		},
		error: function() {
			$('#errorMessages')
				//append a list item onto our ul
				.append($('<li>')
				//turn red
				.attr({class: 'list-group-item list-group-item-danger'})
				//error message associated
				.text('Error calling web service. Please try again later'));
		}
	});
}

//function to clear page upon clicking create contact so
//so we do not see duplicates
function clearContactTable() {
	$('#contentRows').empty();
}

//function to show edit form
//parameter we want to take is contactId
function showEditForm(contactId) {
	//clear out empty messages
	$('#errorMessages').empty();
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/contact/' + contactId,
		//on success, return contact and set on form
		success: function(data, status) {
			$('#edit-first-name').val(data.firstName);
			$('#edit-last-name').val(data.lastName);
			$('#edit-company').val(data.company);
			$('#edit-phone').val(data.phone);
			$('#edit-email').val(data.email);
			$('#edit-contact-id').val(data.contactId);
		},
		error: function() {
			$('#errorMessages')
			//append a list item onto our ul
			.append($('<li>')
			//turn red
			.attr({class: 'list-group-item list-group-item-danger'})
			//error message associated
			.text('Error calling web service. Please try again later'));
		}
	})
	
	//when clicking "edit" hide contact table and show edit form table
	$('#contactTableDiv').hide();
	$('#editFormDiv').show();
}

//adding this function allows to toggle back to contact table
function hideEditForm() {
	//clear out error messages
	$('#errorMessages').empty();
	
	//clear out the form
	$('#edit-first-name').val('');
	$('#edit-last-name').val('');
	$('#edit-company').val('');
	$('#edit-phone').val('');
	$('#edit-email').val('');
	
	//when clicking cancel, go back to contact table
	$('#editFormDiv').hide();
	$('#contactTableDiv').show();
}

//delete contact accepts contactId
function deleteContact(contactId) {
	
	//make ajax call
	$.ajax({
		//http method delete
		type: 'DELETE',
		url: 'http://localhost:8080/contact/' + contactId,
		success: function() {
			loadContacts();
		}
	});
}

//create function to check validity of input
function checkAndDisplayValidationErrors(input) {
	$('#errorMessages').empty();
	
	var errorMessages = [];
	
	input.each(function() {
		if (!this.validity.valid) {
			var errorField = $('label[for=' + this.id +']'). text();
			errorMessages.push(errorField + ' ' + this.validationMessage);
		}
	});
	
	if (errorMessages.length > 0) {
		$.each(errorMessages,function(index,message){
			$('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
		});
		//return true, indicating that there were errors
		return true;
	}else {
		//return false, indicating that there were no errors
		return false;
	}
}
