//JS
$(function() {
    $('.hide').on('click', function() {
        $('p').toggle(1000)
    })

    $('.fade').on('click', function() {
        $('p').fadeToggle(1000)
    })

    $('.slide').on('click', function() {
        $('p').slideToggle(1000)
    })

    $('input').on('change', function() {
        $('p').fadeTo(0, $(this).val())
    })
})