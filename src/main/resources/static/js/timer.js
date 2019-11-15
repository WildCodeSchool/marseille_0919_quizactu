
     
var time = 0;
var initialOffset = '440';
var init = 3;
var i = init;

/* Need initial run as interval hasn't yet occured... */
$('.circle_animation').css('stroke-dashoffset', initialOffset-(initialOffset/3));
$('.circle_animation').css('stroke-dashoffset', initialOffset);

var interval = setInterval(function() {
		$('.timer').text(i);
		if (i == time) {  	
      clearInterval(interval);
      setTimeout(() => { document.location.href="/quiz"}, 500);
    }
    $('.circle_animation').css('stroke-dashoffset', initialOffset-((i+1)*(initialOffset/3)));
		$('.circle_animation').css('stroke-dashoffset', initialOffset - i * initialOffset / init);
    i--;  
}, 1000);