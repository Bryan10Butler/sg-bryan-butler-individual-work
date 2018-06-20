//global variable
var currentMoney = 0;

//load items within doc ready
$(document).ready(function () {
    //load function
    loadItems();

});

//LOAD ITEMS
function loadItems() {

    //clear items table to prevent duplication
    clearItemsTable();
    //make ajax call
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        //on success populate itemArray
        success:function(itemArray){
            //for each item in itemArray
            $.each(itemArray, function(index, item){
                //loop through each element in the item array creating separate div (build div)
                var div = '<div id="' + item.name + '" class="col-lg-3 col-md-3 col-sm-12 well" style="margin-left: 20px;" onclick=showItemNumber('+ item.id +')>';
                //add to div
                //item id display
                div += item.id + '<br>'
                //item name display
                div += '<p align="center">' + item.name +'<br/>';
                //item price display
                div += '$' + item.price.toFixed(2) + '<br/>';
                //item quantity display
                div += 'Quantity: ' + item.quantity;
                div += '</div>';
                //append items within itemArray to new div
                $('#items').append(div);
            })
        },
        //alert error message if load is unsuccessful
        error:function(){
            alert("ERROR: Try Again")
        }
    });
}

//MAKE PURCHASE
$('#makePurchase').click(function(){

    //make ajax call
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080//money/'+ currentMoney +'/item/'+ $('#itemNumber').val(),
        //on success populate data object
        success:function(data){
            //capture new values for quarter, dime, nickel, penny
            $('#changeInput').val(data.quarters + " quarters, " + data.dimes + " dimes, " + data.nickels + " nickels, " + data.pennies + " pennies");
            //display thank you to user
            $('#userMessage').val('Thank you!!!!');
            //empty out total money in vending machine
            $('#moneyInput').val('');
            //empty out item number
            $('#itemNumber').val('');
            //set current money to zero as change is returned upon purchase
            currentMoney=0;
            //load items to capture new quantity
            loadItems();
        },
        error:function(data){
            //re-visit
            var errorMsg = JSON.parse(data.responseText);
            $('#userMessage').val(errorMsg.message)
        }
    });
    //per spec, upon clicking purchase, hide return change button
    $('#returnChange').hide();
});

//RETURN CHANGE
$('#returnChange').click(function(){

    //convert to penny
    var pennyBalance = $('#moneyInput').val()*100;
    //get quarters
    var quarters = Math.floor(pennyBalance / 25);
    pennyBalance %= 25;
    //get dimes
    var dimes = Math.floor(pennyBalance / 10);
    pennyBalance %= 10;
    //get nickels
    var nickels = Math.floor(pennyBalance / 5);
    pennyBalance %= 5;
    //get penny
    var penny = pennyBalance;

    //pass new values to change input when return change is clicked (bottom text area)
    $('#changeInput').val(quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + penny + " pennies")

    //empty money input upon clicking return change (top text area)
    $('#moneyInput').val('');

    //empty user messages upon clicking return change
    $('#userMessage').val('');

    //set current money to zero upon clicking return change
    currentMoney = 0;
});

//ADD MONEY - NOTE ON CLICK IN HTML BUTTONS
function addMoney(amt){

    currentMoney = currentMoney + amt;
    //pass new current money value to money input
    $('#moneyInput').val(currentMoney.toFixed(2));
}

//ON CLICK
$('#moneyButtons').click(function(){

    //show return change button
    $('#returnChange').show();
    //clear user messages
    $('#userMessage').val('');
    //clear return change input
    $('#changeInput').val('');
});

//CLEAR ITEM TABLE (used in load method to clear out)
function clearItemsTable() {
    $('#items').empty();
}

//DISPLAY ITEM NUMBER (passing item ID from our on click function with load)
function showItemNumber(itemId) {
    //grab itemId and put into itemNumber to display
    $('#itemNumber').val(itemId)
}

