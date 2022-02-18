//JS
$(function() {
    $('div').on('click', 'p', function() {
        $(this).css('background-color', 'lightgray')
        $('<p>Parrafo '+$('p').length+'</p>').insertAfter(this).css('background-color', 'yellow')
    })
})