$(document).ready(function () {
	
	$('#akronInfoDiv').hide();
	$('#minneapolisInfoDiv').hide();
	$('#louisvilleInfoDiv').hide();
	
	//event "on" followed by "click"
	$('#akronButton').on('click', function() {
		//show Akron info
		$('#akronInfoDiv').show();
		//hide weather initially
		$('#akronWeather').hide();
		$('#minneapolisInfoDiv').hide();
		$('#louisvilleInfoDiv').hide();
		$('#mainInfoDiv').hide();
	});
	
	//event "on" followed by "click"
	$('#akronWeatherButton').on('click', function() {
		//show Akron weather
		//toggle with number or "slow, medium, fast"
		$('#akronWeather').toggle(1000);
	});
	
	$('#minneapolisButton').on('click', function() {
		$('#minneapolisInfoDiv').show();
		$('#minneapolisWeather').hide();
		$('#akronInfoDiv').hide();
		$('#louisvilleInfoDiv').hide();
		$('#mainInfoDiv').hide();
	});
	$('#minneapolisWeatherButton').on('click', function() {
		$('#minneapolisWeather').toggle(1000);
	});
	
	$('#louisvilleButton').on('click', function () {
		$('#louisvilleInfoDiv').show();
		$('#louisvilleWeather').hide();
		$('#akronInfoDiv').hide();
		$('#minneapolisInfoDiv').hide();
		$('#mainInfoDiv').hide();
	});
	$('#louisvilleWeatherButton').on('click', function() {
		$('#louisvilleWeather').toggle(1000);
	});
	
	//main button
	$('#mainButton').on('click', function() {
		$('#mainInfoDiv').show();
		$('#akronInfoDiv').hide();
		$('#louisvilleInfoDiv').hide();
		$('#minneapolisInfoDiv').hide();
	});
	
	$('tr').hover(
	function () {
		//in
		$(this).children('td').css('background-color', 'WhiteSmoke');
	},
	function() {
		//out
		$(this).children('td').css('background-color', '');
	});
	
	
});