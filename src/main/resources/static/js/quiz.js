$(document).ready(() => {
	let bddTimer = $('#timerQuestion').val();
	let duration = bddTimer ? bddTimer * 1000 : 10000;
	let timer = $('#questionTimer');
	if (timer.length > 0) {
		let oldProgress = 0;
		let startTime;
		function animate(time) {
			if (oldProgress >= 100) {
				setTimeout(() => {
					let questionId = $('#questionId')[0].value;
					document.location.href = '/validateQuestion/' + questionId;
				}, 1000);
			} else {
				let progress = ((time - startTime) / duration) * 100;
				if (progress - oldProgress > 1) {
					timer.css('width', progress + '%');
					oldProgress = progress;
				}
				requestAnimationFrame(animate);
			}
		}
		requestAnimationFrame((time) => {
			startTime = time;
			animate(time);
		});
	}
});