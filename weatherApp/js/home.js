$(document).ready(function () {
	//add a click handler
	$('#weather-button').on('click', function() {
		//create new variable for zip code
		//grab the value of zip-code from user input
		var zipCode = $('#zip-code').val();
		var units = $('#units').val();
		//call to ajax
		$.ajax({
			url: 'https://api.openweathermap.org/data/2.5/weather?zip=' + zipCode + '&,us' + '&units=' + units + '&APPID=2c6e90fce00eb61913204ec8f2c7eccb',
			//HTTP method 'GET'
			type: 'GET',
			dataType: 'json',
			//on success, hand us back whatever came back from server
			success: function(weatherData) {
				//Hard code is single quotes
				var currentConditionImage = '<img src="http://openweathermap.org/img/w/' + weatherData.weather[0].icon + '.png"/>';
				
				//City Name
				$('#currentConditions h2').html('Current Conditions in: ' + weatherData.name);
				//Haze
				$('#conditionImage h4').html(currentConditionImage + weatherData.weather[0].main + ': ' + weatherData.weather[0].description);
				$('#temp').html('Temp: ' + weatherData.main.temp);
				$('#humidity').html('Humidity: ' + weatherData.main.humidity);
				$('#wind').html('Wind: ' + weatherData.wind.speed);
				
				//empty the value
				$('#zip-code').val('');
				
				//console.log(weatherData);
				
				//five day url
			}
		});
		
		$.ajax({
			type: 'GET',
			url: 'https://api.openweathermap.org/data/2.5/forecast?zip=' + zipCode + '&,us' + '&units=' + units + '&APPID=2c6e90fce00eb61913204ec8f2c7eccb', 
			success: function (fiveDayForecast) {
				
				var dayOneImg = '<img src="http://openweathermap.org/img/w/' + fiveDayForecast.list[3].weather[0].icon + '.png"/>';
				var dayTwoImg = '<img src="http://openweathermap.org/img/w/' + fiveDayForecast.list[11].weather[0].icon + '.png"/>';
				var dayThreeImg = '<img src="http://openweathermap.org/img/w/' + fiveDayForecast.list[19].weather[0].icon + '.png"/>';
				var dayFourImg = '<img src="http://openweathermap.org/img/w/' + fiveDayForecast.list[27].weather[0].icon + '.png"/>';
				var dayFiveImg = '<img src="http://openweathermap.org/img/w/' + fiveDayForecast.list[35].weather[0].icon + '.png"/>';
				
				$('#fiveDay h2').html('Five Day Forecast: ');
				
				$('#day1').html(fiveDayForecast.list[3].dt_txt+'<br>');
				$('#day1').append(dayOneImg + fiveDayForecast.list[3].weather[0].description+'<br>');
				$('#day1').append('H: ' + fiveDayForecast.list[3].main.temp_max + ' ' + 'L: ' + fiveDayForecast.list[3].main.temp_min);
				
				$('#day2').html(fiveDayForecast.list[11].dt_txt+'<br>');
				$('#day2').append(dayTwoImg + fiveDayForecast.list[11].weather[0].description+'<br>');
				$('#day2').append('H: ' + fiveDayForecast.list[11].main.temp_max + ' ' + 'L: ' + fiveDayForecast.list[11].main.temp_min);
				
				$('#day3').html(fiveDayForecast.list[19].dt_txt+'<br>');
				$('#day3').append(dayThreeImg + fiveDayForecast.list[19].weather[0].description+'<br>');
				$('#day3').append('H: ' + fiveDayForecast.list[19].main.temp_max + ' ' + 'L: ' + fiveDayForecast.list[19].main.temp_min);
				
				$('#day4').html(fiveDayForecast.list[27].dt_txt+'<br>');
				$('#day4').append(dayFourImg + fiveDayForecast.list[27].weather[0].description+'<br>');
				$('#day4').append('H: ' + fiveDayForecast.list[27].main.temp_max + ' ' + 'L: ' + fiveDayForecast.list[27].main.temp_min);
				
				$('#day5').html(fiveDayForecast.list[35].dt_txt+'<br>');
				$('#day5').append(dayFiveImg + fiveDayForecast.list[35].weather[0].description+'<br>');
				$('#day5').append('H: ' + fiveDayForecast.list[35].main.temp_max + ' ' + 'L: ' + fiveDayForecast.list[35].main.temp_min);
				
			//console.log(fiveDayForecast.list[3].dt_txt);
				
				
			},
			
			error: function () {
				
			}
		})//end of .ajax
	});
});

