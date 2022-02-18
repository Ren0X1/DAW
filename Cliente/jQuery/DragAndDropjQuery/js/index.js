//JS
$(function() {
    $( '.item' ).draggable({
        helper: 'clone'
    })

    $( '.day' ).droppable({
        accept: '.item',
        hoverClass: 'hovering',
        drop: function(ev,ui) {
            ui.draggable.detach()
            $(this).append(ui.draggable)
        }
    })
})