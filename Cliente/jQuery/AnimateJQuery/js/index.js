//JS
$(function() {
    $('button').click(function() {
        startAnimation()
    })
    
    function startAnimation(){
        $('div').animate({height: 300}, "slow");
        $('div').animate({width: 300}, "slow");
        $('div').animate({height: 100}, "slow");
        $('div').animate({width: 100}, "slow", startAnimation);
    }
})