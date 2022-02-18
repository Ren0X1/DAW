//JS
$(function() {
    $('input').click(function() {
        $(this).toogle1(
            function(v) {$(v).css("background-color", "yellow")},
            function(v) {$(v).css("background-color", "red")},
            function(v) {$(v).css("background-color", "blue")}
        )
    })

    $.fn.toogle1 = function() {
        let x = $(this).data("index")||0
        arguments[x](this)
        if (x>=arguments.length-1) {
            x=-1
        }
        $(this).data("index", x+1)
    }
})