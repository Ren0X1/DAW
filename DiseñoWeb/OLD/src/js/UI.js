/*
Handles events for the player user interface
Usage: AUDIO.UI.init();
*/

'use strict'

AUDIO.UI = (function () {

	var play = $('#play'),
		next = $('#next'),
		previous = $('#previous'),
		loading = $('#loading'),
		playToggle = $('#play-toggle');

	function updatePlayButton () {
		var track = AUDIO.Player.getCurrentTrack();
		//if playing
		if (track.isPlaying()) {
			//show pause button
			playToggle.removeClass('fa-play');
			playToggle.addClass('fa-pause');
		} else {
			//show play button
			playToggle.removeClass('fa-pause');
			playToggle.addClass('fa-play');
		}
	}

	function onPlayClicked () {
		play.click(function () {
			AUDIO.Player.togglePause();
			updatePlayButton();
		});
	}

	function onNextClicked () {

		next.click(function () {
			AUDIO.Player.nextTrack();
			updatePlayButton();
		});
	}

	function onPreviousClicked () {
		previous.click(function () {
			AUDIO.Player.previousTrack();
			updatePlayButton();
		});
	}

	/* 
	TODO
	onMuteClicked - toggleMute
	onVolumeChanged - handleVolumeChanged
	onPositionChanged - handlePositionChanged
	*/

	function init () {
		onPlayClicked();
		onNextClicked();
		onPreviousClicked();
		console.log('UI initiated');
	}

	function update () {
		updatePlayButton();
		//update progress bar
		//update time
	}

	function draw () {

	}

	function cleanup () {

	}

	return {
		init: init,
		update: update,
	}

})();

