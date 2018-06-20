$(document).ready(function () {
	
	$('H1').addClass('text-center');
	$('H2').addClass('text-center');
	$('.myBannerHeading').removeClass('myBannerHeading').addClass("page-header");
	$('#yellowHeading').text("Yellow Team");
	$('#orangeTeamList').css({'background-color': 'orange'});
	$('#blueTeamList').css({'background-color': 'cornFlowerBlue'});
	$('#redTeamList').css({'background-color': 'red'});
	$('#yellowTeamList').css({'background-color': 'yellow'});
	$('#yellowTeamList').append('<li>Joseph Banks</li>');
	$('#yellowTeamList').append('<li>Simon Jones</li>');
	$('#oops').hide();
	$('#footerPlaceholder').remove();
	$('#footer').append('<p style="font-family:courier; font-size:50px; text-align:center">BryanButler@mail.com</p>')
    
});