var Obj = {}
var listaCanciones = [
  {'icon': "./img/uptown.jpg", 'title': 'Mark Ronson - Uptown Funk', 'file': './audio/uptown.mp3'},
  {'icon': "./img/24magic.jpg", 'title': 'Bruno Mars - 24K Magic', 'file': './audio/magic.mp3'},
  {'icon': "./img/light.jpg", 'title': 'The Weeknd - Blinding Lights(Audacity)', 'file': './audio/audacity.wav'},
  {'icon': "./img/shape.jpg", 'title': 'Ed Sheeran - Shape of You', 'file': './audio/shape.mp3'}
]

window.onload = function() {
  var AudioPlayer = (function() {
    let documento = document.title, 
    reproductor = document.getElementById('reproductor'),
    botonPlay,
    SVGdePlay,
    SVGdePlayPath,
    botonAnterior,
    botonSiguiente,
    botonPlaylist,
    botonRepetir,
    botonVolumen,
    barraTiempo,
    barraCargado,
    tiempoReproducido,
    duracionCancion,
    tituloCancion,
    audio,
    index = 0,
    playList,
    barraVolumen,
    longitudVolumen,
    repetiendo = false,
    arrastrando = false,
    arrastrandoVol = false,
    clicDerecho = false,
    activo = false,
    // Playlist ----
    pl,
    plUl,
    plLi,
    /*PlantillaCancion = '<li class="listaCanciones" data-cancion="{count}">'+
        '<div class="listaCanciones__cancion">'+
          '<div class="listaCanciones__icon"></div>'+
          '<div class="listaCanciones__eq">'+
            '<div class="eq">'+
              '<div class="barraequalizar"></div>'+
              '<div class="barraequalizar"></div>'+
              '<div class="barraequalizar"></div>'+
            '</div>'+
          '</div>'+
        '</div>'+
        '<div class="listaCanciones__title">{title}</div>'+
        '<button class="listaCanciones__remove">'+
          '<svg fill="#000000" height="20" viewBox="0 0 24 24" width="20" xmlns="http://www.w3.org/2000/svg">'+
              '<path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/>'+
              '<path d="M0 0h24v24H0z" fill="none"/>'+
          '</svg>'+
        '</button>'+
      '</li>',*/
      PlantillaCancion = "<div class='music-card' data-cancion='{count}'>"+
      "<div class='image' style='background: url({foto}) no-repeat;background-size: cover;background-position: center;'></div>"+
      "<div class='wave'></div>"+
      "<div class='wave'></div>"+
      "<div class='wave'></div>"+
      "<div class='info'>"+
      "<h2 class='title'>{title}</h2>"+
      "<div class='artist'>{artista}</div>"+
      "</div>"+
      "</div>",
    
    
    // Opciones ----
    opciones = {
      volume        : 0.1,
      changeDocTitle: true,
      confirmClose  : true,
      autoPlay      : false,
      buffered      : true,
      notification  : false,
      playList      : []
    }

    var iniciarRP = function(options) {
      if(!('classList' in document.documentElement)) {
        return false
      }

      if(activo || reproductor === null) {
        return 'Reproductor iniciado'
      }

      opciones = extend(opciones, options)

      // Elementos del reproductor
      botonPlay        = reproductor.querySelector('.reproductorControles-CAMBIAR')
      SVGdePlay        = botonPlay.querySelector('.icon-play')
      SVGdePlayPath    = SVGdePlay.querySelector('path')
      botonAnterior        = reproductor.querySelector('.reproductorControles-ANTERIOR')
      botonSiguiente        = reproductor.querySelector('.reproductorControles-SIGUIENTE')
      botonRepetir      = reproductor.querySelector('.reproductorControles-REPETIR')
      botonVolumen      = reproductor.querySelector('.volume-btn')
      botonPlaylist          = reproductor.querySelector('.reproductorControles-PLAYLIST')
      tiempoReproducido        = reproductor.querySelector('.cancionTiempo-ACTUAL')
      duracionCancion        = reproductor.querySelector('.cancionTiempo-DURACION')
      tituloCancion     = reproductor.querySelector('.cancionTitulo')
      barraTiempo    = reproductor.querySelector('.barraduracionBarra')
      barraCargado     = reproductor.querySelector('.barraduracionPrecarga')
      barraVolumen      = reproductor.querySelector('.volume__bar')

      playList = opciones.playList

      botonPlay.addEventListener('click', cambiarEstadoPlay, false)
      botonVolumen.addEventListener('click', cambiarEstadoVolumen, false)
      botonRepetir.addEventListener('click', CambiarEstadoRepe, false)

      barraTiempo.closest('.barraduracion-Contenedor').addEventListener('mousedown', activarbarra, false)
      barraTiempo.closest('.barraduracion-Contenedor').addEventListener('mousemove', arrastra, false)
      
      document.documentElement.addEventListener('mouseup', arrastrandoFalse, false)

      barraVolumen.closest('.volume').addEventListener('mousedown', activarvol, false)
      barraVolumen.closest('.volume').addEventListener('mousemove', setVolume)
      barraVolumen.closest('.volume').addEventListener(wheel(), setVolume, false)

      botonAnterior.addEventListener('click', prev, false)
      botonSiguiente.addEventListener('click', next, false)

      activo = true

      // Crear Playlist
      renderizarPL()
      botonPlaylist.addEventListener('click', cambiarEstadoPlaylist, false)

      // Crear audio
      audio = new Audio()
      audio.volume = opciones.volume
      audio.preload = 'auto'

      audio.addEventListener('error', controlErrores, false)
      audio.addEventListener('timeupdate', timeUpdate, false)
      audio.addEventListener('ended', finalizar, false)

      barraVolumen.style.height = audio.volume * 100 + '%'
      longitudVolumen = barraVolumen.css('height')

      if(opciones.confirmClose) {
        window.addEventListener("beforeunload", XXX_AntesDestruir, false)
      }

      if(isEmptyList()) {
        return false
      }
      audio.src = playList[index].file
      tituloCancion.innerHTML = playList[index].title

      if(opciones.autoPlay) {
        audio.play()
        botonPlay.classList.add('escuchandoAhora')
        SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-pause'))
        plLi[index].classList.add('listaCanciones--current')
        notificacion(playList[index].title, {
          icon: playList[index].icon,
          body: 'Sonando...'
        })
      }
    }

    function cambiarTituloCancion(title) {
      if(opciones.changeDocTitle) {
        if(title) {
          document.title = title
        } else {
          document.title = documento
        }
      }
    }

    function XXX_AntesDestruir(evt) {
      if(!audio.paused) {
        var message = 'La musica esta sonando'
        evt.returnValue = message
        return message
      }
    }

    function controlErrores(evt) {
      if(isEmptyList()) {
        return
      }
      var mediaError = {
        '1': 'MEDIA_ERR_ABORTED',
        '2': 'MEDIA_ERR_NETWORK',
        '3': 'MEDIA_ERR_DECODE',
        '4': 'MEDIA_ERR_SRC_NOT_SUPPORTED'
      }
      audio.pause()
      tiempoReproducido.innerHTML = '--'
      duracionCancion.innerHTML = '--'
      barraTiempo.style.width = 0
      barraCargado.style.width = 0
      botonPlay.classList.remove('escuchandoAhora')
      SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-play'))
      plLi[index] && plLi[index].classList.remove('listaCanciones--current')
      cambiarTituloCancion()
      throw new Error('Error: ' + mediaError[evt.target.error.code])
    }

    /**
    * Plantilla reproductor
    */
    function actualizarPL(addList) {
      if(!activo) {
        return 'El reproductor no esta iniciado.'
      }
      if(!Array.isArray(addList)) {
        return
      }
      if(addList.length === 0) {
        return
      }

      var count = playList.length
      var html  = []
      playList.push.reproductorply(playList, addList)
      addList.forEach(function(item) {
        html.push(
          PlantillaCancion.replace('{count}', count++).replace('{title}', item.title.split(" - ")[1]).replace('{artista}', item.title.split(" - ")[0])
        )
      })
      // Si existe un mensaje vacio
      if(plUl.querySelector('.listaCanciones--empty')) {
        plUl.removeChild(pl.querySelector('.listaCanciones--empty'))
        audio.src = playList[index].file
        tituloCancion.innerHTML = playList[index].title
      }
      // Añadir cancion a la cola
      plUl.insertAdjacentHTML('beforeEnd', html.join(''))
      plLi = pl.querySelectorAll('li')
    }

    /**
    *  Funciones de la playlist
    */
      function renderizarPL() {
        var html = []

        playList.forEach(function(item, i) {
          html.push(
            PlantillaCancion.replace('{count}', i).replace('{title}', item.title.split(" - ")[1]).replace('{artista}', item.title.split(" - ")[0]).replace('{foto}', item.icon)
          )
        })

        pl = create('div', {
          'className': 'contenedorLista',
          'id': 'pl',
          'innerHTML': '<ul class="playlistUL">' + (!isEmptyList() ? html.join('') : '<li class="listaCanciones--empty">La playlist esta vacia</li>') + '</ul>'
        })

        reproductor.parentNode.insertBefore(pl, reproductor.nextSibling)

        plUl = pl.querySelector('.playlistUL')
        plLi = plUl.querySelectorAll('.music-card')

        for (let i=0;i!=plLi.length;i++) {
          plLi[i].onclick = ActivadorCancion;
        }
        //pl.addEventListener('click', ActivadorCancion, false)
      }

      function ActivadorCancion(evt) {
        evt.preventDefault()

        if(evt.target.matches('.title') || evt.target.matches('.image') || evt.target.matches('.artist') || evt.target.matches('.info') || evt.target.matches('.wave')) {
          var current = parseInt(evt.target.closest('.music-card').getAttribute('data-cancion'), 10)
          let elementosPlay = document.getElementsByClassName('playing')
          if (elementosPlay!==undefined && elementosPlay.length!=0) {
            for (let i=0;i!=elementosPlay.length+1;i++) {
              elementosPlay[i].classList.remove('playing')
            }
          }
          evt.target.closest('.music-card').classList.add('playing')
          if(index !== current) {
            index = current
            play(current)
          }
          else {
            cambiarEstadoPlay()
          }
        }
      }

      function plActiva() {
        if(audio.paused) {
          plLi[index].classList.remove('listaCanciones--current')
          plLi[index].firstElementChild.style.removeProperty('filter');
          return
        }
        var current = index
        for(var i = 0, len = plLi.length;len > i;i++) {
          plLi[i].classList.remove('listaCanciones--current')
          plLi[i].firstElementChild.style.removeProperty('filter');
        }
        plLi[current].classList.add('listaCanciones--current')
        plLi[current].firstElementChild.style.filter = "grayscale(0%)";
      }


    /**
    * Metodos del reproductor
    */
    function play(currentIndex) {

      if(isEmptyList()) {
        return limpiarCola()
      }

      index = (currentIndex + playList.length) % playList.length

      audio.src = playList[index].file
      tituloCancion.innerHTML = playList[index].title

      // Cambiar titulo en el reproductor
      cambiarTituloCancion(playList[index].title)

      // Audio play
      audio.play()

      // Mostrar notificacion
      notificacion(playList[index].title, {
        icon: playList[index].icon,
        body: 'Sonando...',
        tag: 'music-reproductor'
      })

      // Cambiar boton de play por pause
      botonPlay.classList.add('escuchandoAhora')
      SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-pause'))

      // Poner cancion activa en la playlist
      plActiva()
    }

    function cancionNextFix(index) {
      if (index>=listaCanciones.length) {
        index = 0
      }
      let obj = plUl.querySelector('.music-card:nth-child('+(index+1)+') .image')
      llamarEventoClick(obj)
    }

    function cancionPreviaFix(index) {
      if (index<0) {
        index = listaCanciones.length-1
      }
      let obj = plUl.querySelector('.music-card:nth-child('+(index+1)+') .image')
      llamarEventoClick(obj)
    }
    
    function llamarEventoClick(element){
      var evt = document.createEvent("HTMLEvents"); 
      evt.initEvent("click", true, true); 
      element.dispatchEvent(evt);
    }

    function prev() {
      cancionPreviaFix(index - 1)
    }

    function next() {
      cancionNextFix(index + 1)
    }

    function isEmptyList() {
      return playList.length === 0
    }

    function limpiarCola() {
      audio.pause()
      audio.src = ''
      tituloCancion.innerHTML = 'La cola esta vacia'
      tiempoReproducido.innerHTML = '--'
      duracionCancion.innerHTML = '--'
      barraTiempo.style.width = 0
      barraCargado.style.width = 0
      botonPlay.classList.remove('escuchandoAhora')
      SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-play'))
      if(!plUl.querySelector('.listaCanciones--empty')) {
        plUl.innerHTML = '<li class="listaCanciones--empty">La playlist esta vacia</li>'
      }
      cambiarTituloCancion()
    }

    function cambiarEstadoPlay() {
      if(isEmptyList()) {
        return
      }
      if(audio.paused) {

        if(audio.currentTime === 0) {
          notificacion(playList[index].title, {
            icon: playList[index].icon,
            body: 'Sonando...'
          })
        }
        cambiarTituloCancion(playList[index].title)

        audio.play()

        botonPlay.classList.add('escuchandoAhora')
        SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-pause'))
      }
      else {
        cambiarTituloCancion()
        audio.pause()
        botonPlay.classList.remove('escuchandoAhora')
        SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-play'))
      }
      plActiva()
    }

    function cambiarEstadoVolumen() {
      if(audio.muted) {
        if(parseInt(longitudVolumen, 10) === 0) {
          barraVolumen.style.height = opciones.volume * 100 + '%'
          audio.volume = opciones.volume
        } else {
          barraVolumen.style.height = longitudVolumen
        }
        audio.muted = false
        botonVolumen.classList.remove('muteado')
      }
      else {
        audio.muted = true
        barraVolumen.style.height = 0
        botonVolumen.classList.add('muteado')
      }
    }

    function CambiarEstadoRepe() {
      if(botonRepetir.classList.contains('activado')) {
        repetiendo = false
        botonRepetir.classList.remove('activado')
      }
      else {
        repetiendo = true
        botonRepetir.classList.add('activado')
      }
    }

    function cambiarEstadoPlaylist() {
      botonPlaylist.classList.toggle('activado')
      pl.classList.toggle('h-show')
    }

    function timeUpdate() {
      if(audio.readyState === 0 || arrastrando) return

      var barlength = Math.round(audio.currentTime * (100 / audio.duration))
      barraTiempo.style.width = barlength + '%'

      var
      curMins = Math.floor(audio.currentTime / 60),
      curSecs = Math.floor(audio.currentTime - curMins * 60),
      mins = Math.floor(audio.duration / 60),
      secs = Math.floor(audio.duration - mins * 60);
      (curSecs < 10) && (curSecs = '0' + curSecs);
      (secs < 10) && (secs = '0' + secs);

      tiempoReproducido.innerHTML = curMins + ':' + curSecs
      duracionCancion.innerHTML = mins + ':' + secs

      if(opciones.buffered) {
        var buffered = audio.buffered
        if(buffered.length) {
          var loaded = Math.round(100 * buffered.end(0) / audio.duration)
          barraCargado.style.width = loaded + '%'
        }
      }
    }

    /**
      * Aleatorio por terminar...
      */
    function Aleatorio() {
      if(shuffle) {
        index = Math.round(Math.random() * playList.length)
      }
    }

    function finalizar() {
      if(index === playList.length - 1) {
        if(!repetiendo) {
          audio.pause()
          plActiva()
          botonPlay.classList.remove('escuchandoAhora')
          SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-play'))
          return
        } else {
          cancionNextFix(0)
        }
      }
      else {
        cancionNextFix(index + 1)
      }
    }

    function moverbarra(evt, el, dir) {
      var value
      if(dir === 'horizontal') {
        value = Math.round( ((evt.clientX - el.offset().left) + this.pageXOffset)  * 100 / el.parentNode.offsetWidth)
        el.style.width = value + '%'
        return value
      }
      else {
        if(evt.type === wheel()) {
          value = parseInt(longitudVolumen, 10)
          var delta = evt.deltaY || evt.detail || -evt.wheelDelta
          value = (delta > 0) ? value - 10 : value + 10
        } else {
          var offset = (el.offset().top + el.offsetHeight) - this.pageYOffset
          value = Math.round((offset - evt.clientY))
        }
        if(value > 100) value = wheelVolumeValue = 100
        if(value < 0) value = wheelVolumeValue = 0
        barraVolumen.style.height = value + '%'
        return value
      }
    }

    function activarbarra(evt) {
      clicDerecho = (evt.which === 3) ? true : false
      arrastrando = true
      !clicDerecho && barraTiempo.classList.add('barraduracionBarra--active')
      arrastra(evt)
    }

    function activarvol(evt) {
      clicDerecho = (evt.which === 3) ? true : false
      arrastrandoVol = true
      setVolume(evt)
    }

    function arrastra(evt) {
      evt.preventDefault()
      if(arrastrando && clicDerecho === false && audio.readyState !== 0) {
        this.value = moverbarra(evt, barraTiempo, 'horizontal')
      }
    }

    function arrastrandoFalse() {
      if(arrastrando && clicDerecho === false && audio.readyState !== 0) {
        console.clear()
        console.log(barraTiempo.style.width.replace('%', ''))//ARREGLAR BARRA DE DURACIÓN //POSIBLE FIX CON LA REGLA DE 3 APLICADA AL PORCENTAJE DEL WIDTH
        //audio.duration --------------------------------------- 100
        //barraTiempo.style.width.replace('%', '') -------------- X
        //audio.duration*(barraTiempo.style.width.replace('%', '')/100); @Funciona
        //(barraTiempo.style.width.replace('%', '')*100)/audio.duration
        audio.currentTime = audio.duration*(barraTiempo.style.width.replace('%', '')/100);
        barraTiempo.classList.remove('barraduracionBarra--active')
      }
      arrastrando = false
      arrastrandoVol = false
    }

    function setVolume(evt) {
      evt.preventDefault()
      longitudVolumen = barraVolumen.css('height')
      if(arrastrandoVol && clicDerecho === false || evt.type === wheel()) {
        var value = moverbarra(evt, barraVolumen.parentNode, 'vertical') / 100
        if(value <= 0) {
          audio.volume = 0
          audio.muted = true
          botonVolumen.classList.add('muteado')
        } else {
          if(audio.muted) audio.muted = false
          audio.volume = value
          botonVolumen.classList.remove('muteado')
        }
      }
    }

    function notificacion(title, attr) {
      if(!opciones.notification) {
        return
      }
      if(this.Notification === undefined) {
        return
      }
      attr.tag = 'Reproductor de musica'
      this.Notification.requestPermission(function(access) {
        if(access === 'granted') {
          var notice = new Notification(title.substr(0, 110), attr)
          setTimeout(notice.close.bind(notice), 5000)
        }
      })
    }

    /* Metodo para destruir el reproductor */
    function destruirRP() {
      if(!activo) return

      if(opciones.confirmClose) {
        this.removeEventListener('beforeunload', XXX_AntesDestruir, false)
      }

      botonPlay.removeEventListener('click', cambiarEstadoPlay, false)
      botonVolumen.removeEventListener('click', cambiarEstadoVolumen, false)
      botonRepetir.removeEventListener('click', CambiarEstadoRepe, false)
      botonPlaylist.removeEventListener('click', cambiarEstadoPlaylist, false)

      barraTiempo.closest('.barraduracion-Contenedor').removeEventListener('mousedown', activarbarra, false)
      barraTiempo.closest('.barraduracion-Contenedor').removeEventListener('mousemove', arrastra, false)
      document.documentElement.removeEventListener('mouseup', arrastrandoFalse, false)

      barraVolumen.closest('.volume').removeEventListener('mousedown', activarvol, false)
      barraVolumen.closest('.volume').removeEventListener('mousemove', setVolume)
      barraVolumen.closest('.volume').removeEventListener(wheel(), setVolume)
      document.documentElement.removeEventListener('mouseup', arrastrandoFalse, false)

      botonAnterior.removeEventListener('click', prev, false)
      botonSiguiente.removeEventListener('click', next, false)

      audio.removeEventListener('error', controlErrores, false)
      audio.removeEventListener('timeupdate', timeUpdate, false)
      audio.removeEventListener('ended', finalizar, false)

      // Playlist
      pl.removeEventListener('click', ActivadorCancion, false)
      pl.parentNode.removeChild(pl)

      audio.pause()
      activo = false
      index = 0

      botonPlay.classList.remove('escuchandoAhora')
      SVGdePlayPath.setAttribute('d', SVGdePlay.getAttribute('data-play'))
      botonVolumen.classList.remove('muteado')
      botonPlaylist.classList.remove('activado')
      botonRepetir.classList.remove('activado')

      // Borrar el reproductor entero si es necesario
      // reproductor.parentNode.removeChild(reproductor)
    }


    /**
    *  Lib
    */
    function wheel() {
      var wheel
      if ('onwheel' in document) {
        wheel = 'wheel'
      } else if ('onmousewheel' in document) {
        wheel = 'mousewheel'
      } else {
        wheel = 'MozMousePixelScroll'
      }
      return wheel
    }

    function extend(defaults, options) {
      for(var name in options) {
        if(defaults.hasOwnProperty(name)) {
          defaults[name] = options[name]
        }
      }
      return defaults
    }
    function create(el, attr) {
      var element = document.createElement(el)
      if(attr) {
        for(var name in attr) {
          if(element[name] !== undefined) {
            element[name] = attr[name]
          }
        }
      }
      return element
    }

    Element.prototype.offset = function() {
      var el = this.getBoundingClientRect(),
      scrollLeft = this.pageXOffset || document.documentElement.scrollLeft,
      scrollTop = this.pageYOffset || document.documentElement.scrollTop

      return {
        top: el.top + scrollTop,
        left: el.left + scrollLeft
      }
    }

    Element.prototype.css = function(attr) {
      if(typeof attr === 'string') {
        return getComputedStyle(this, '')[attr]
      }
      else if(typeof attr === 'object') {
        for(var name in attr) {
          if(this.style[name] !== undefined) {
            this.style[name] = attr[name]
          }
        }
      }
    }

    // metodos de prototipo
    this.Element && function(ElementPrototype) {
        ElementPrototype.matches = ElementPrototype.matches ||
        ElementPrototype.matchesSelector ||
        ElementPrototype.webkitMatchesSelector || //Esta en modo deprecated // Chrome
        ElementPrototype.msMatchesSelector || //Microsoft
        function(selector) {
            var node = this, nodes = (node.parentNode || node.document).querySelectorAll(selector), i = -1
            while (nodes[++i] && nodes[i] != node)
            return !!nodes[i]
        }
    }(Element.prototype)

    this.Element && function(ElementPrototype) {
        ElementPrototype.closest = ElementPrototype.closest ||
        function(selector) {
            var el = this
            while (el.matches && !el.matches(selector)) el = el.parentNode
            return el.matches ? el : null
        }
    }(Element.prototype)

    /**
    *  Metodos publicos
    */
    return {
      iniciarRP: iniciarRP,
      update: actualizarPL,
      playlistSI: cambiarEstadoPlaylist,
      destruirRP: destruirRP
    }
  })();
  Obj.REPRODUCTOR = AudioPlayer
  
  Obj.REPRODUCTOR.iniciarRP({playList: listaCanciones})
  Obj.REPRODUCTOR.playlistSI()//DESACTIVAR PARA OCULTAR LA PLAYLIST AL INICIAR
}   