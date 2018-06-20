$(document).ready(function(){
    loadDvd();

});

//LOAD DVD ITEMS
function loadDvd() {
    clearDvdTable();

    //call content rows from HTML and store as content rows
    var contentRows = $('#contentRows');
    //make ajax call to our web service
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/dvds',
        //on success pass information to dvdArray
        success: function(dvdArray){
            //for each dvd in dvdArray
            $.each(dvdArray, function(index, dvd){
                //assign data to rows
                var editDvd = dvd.dvdId;
                var row = '<tr>';
                    row += '<td><a onclick="showDetailsForm()">' + dvd.title + '</a></td>';
                    row += '<td>' + dvd.releaseYear + '</td>';
                    row += '<td>' + dvd.director + '</td>';
                    row += '<td>' + dvd.rating + '</td>';
                    row += '<td><a onclick="showEditForm(' + editDvd +')">Edit |</a><a onclick="deleteContact(' + editDvd + ')"> Delete</a></td>';
                    row += '</tr>'
                contentRows.append(row);
            });
        },
        error: function(){
            $('#errorMessages')
            //append a list item onto our ul
            .append($('<li>'))
            //turn red
            .attr({class: 'list-group-item list-group-item-danger'})
            //error message associated
            .text('Error calling web service. Please try again later');
        }
    });
}

//function to clear page so we do not see duplicates
function clearDvdTable() {
    $('#contentRows').empty();
}

//CREATE DVD
//CHECK THIS
$('#createDvdButton').click(function(){
   $.ajax({
       type: 'POST',
       url: 'http://localhost:8080/dvd',
       //send data as JSON string
       data: JSON.stringify({
          //grab element on form and value input
           title: $('#dvdTitle').val(),
           releaseDate: $('#releaseYear'),
           director: $('#Director'),
           rating: $('#rating'),
           notes: $('#notes'),
       }),
       //information about data
       headers:{
         //accept JSON format
         'Accept': 'application/json',
         'content-Type': 'application/json'
       },
       //our data type
       'dataType': 'json',
       //what action to take upon success
       success: function() {
           $('#errorMessages').empty();
           $('#dvdTitle').val('');
           $('#releaseYear').val('');
           $('#Director').val('');
           $('#rating').val('');
           $('#notes').val('');
           loadDvd();
       },
       error: function () {
           $('#errorMessages')
           //append a list item onto our ul
           .append($('<li>'))
           //turn red
           .attr({class: 'list-group-item list-group-item-danger'})
           //error message associated
           .text('Error calling web service. Please try again later');
       }
   })
});

