//------------------------------------------------
function creaXMLHttpR(){
    if (window.XMLHttpRequest) {
        // code for modern browsers
        xmlhttp = new XMLHttpRequest();
     } else {
        // code for old IE browsers
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}
//------------------------------------------------------------------------------
function enviarPeticion(metodo,url,asincrono,procesaRespuesta,procesaError,datos){
    
    let myPromise = new Promise(function(myResolve, myReject) {
   //1- crear el xhttp 
    var xhttp = new XMLHttpRequest();
    
   //2.- programamos el evento
   
   xhttp.onreadystatechange = function() {
    if (this.readyState == 4 )
      if(this.status == 200) 
        //procesaRespuesta(this.response);
         myResolve(this.response);   
    
      else myReject(this.statusText);
  };
  if(metodo=="GET"){
    xhttp.open(metodo, url+"?"+datos,asincrono);
    xhttp.send(); 
  } 
  else{
   xhttp.open(metodo, url,asincrono);
   xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
   //xhttp.setRequestHeader("Content-type","application/json");
   xhttp.send(datos);   
  }
    });//promesa
    
    myPromise.then(
            function (value){procesaRespuesta(value)},
            function (eror){procesaError(error)}
            )
    
}
//-------------------------------------------------------------------------------------

function enviarPeticionFetch(metodo,url,asincrono,procesaRespuesta,procesaError,datos){
    
   const options = {
    method: metodo,
    headers:{}
    };
    if(metodo=="POST"){
        options.body=datos;
        options.headers["Content-Type"]="application/x-www-form-urlencoded";
    }
    else url+="?"+datos;
  
    // Petici�n HTTP
    fetch(url, options)
          .then(response => {
                 if (response.ok)
                        return response.text()
                else
                        throw new Error(response.status+":"+response.statusText);
            })
        .then(data => {
                alert("Datos: " + data);
                procesaRespuesta(data);
        })
        .catch(err => {
                alert("ERROR: ", err.message);
                procesaError( err.message);
        });
}



//-------------------------------------------------------------------------------
function loadDocXML(fichero,asinc,procesaRepuesta,id){
      
  //var xhttp = creaXMLHttpR();
  var xhttp = new XMLHttpRequest();
  
  xhttp.onload=function(){
       
       procesaRepuesta(this.responseXML,id);
       
  }
  
  xhttp.open("GET", fichero, asinc);
  
  xhttp.send();
  
 // return xhttp.responseXML;
} 
//-------------------------------------------------------------------------------
//------------------Cargar CD's en la lista y en un Objeto-----------------------
                var xmlDoc;
		var xslDoc;
		var listaCds={};
			
		function lineacd(cd,Artista,Pais,precio,color){
				this.Titulo=cd;
				this.Artista=Artista;
				this.Pais=Pais;
				this.Precio=precio;
				this.Color=color;
				}
//----------------------------------------------------------------			
		function Cargar(xmlDocc,id){
                  
                  xmlDoc= xmlDocc; 
                    //xmlDoc=loadDocXML("xml/cdcatalog.xml",false);
                    //xslDoc=loadDocXML("xml/cdcatalog.xsl",true);
                 
                var lista=document.getElementById(id);
				
		let tit=xmlDoc.getElementsByTagName("title");
		let aut=xmlDoc.getElementsByTagName("artist");
		let cou=xmlDoc.getElementsByTagName("country");
		let pre=xmlDoc.getElementsByTagName("price");
                
                for (i=0;i<tit.length;i++) {
                    //1.- Cargar el select
                    lista.options.add(new Option( tit[i].childNodes[0].nodeValue +" / "+
				  		  aut[i].childNodes[0].nodeValue,
                                                  pre[i].childNodes[0].nodeValue ) );
                    
                    //2.- Cargar el objeto
                    linea = new lineacd(tit[i].childNodes[0].nodeValue,
                                	aut[i].childNodes[0].nodeValue,
					cou[i].childNodes[0].nodeValue,
					pre[i].childNodes[0].nodeValue,
					colorAleatorio());
                    listaCds[i]=linea;
                }
                
				
		//cargar la lista id, crear objeto y llamar a creaTabla......................
		
		// creaTabla("lista de CD's","carro","catalogo",listaCds,false);				
                
		}
//------------------------------------------------------------------------------------
function displayResult(donde,xml,xsl,boton)
		{
			var catalogo=document.getElementById(donde);
			if(catalogo.childNodes.length!==0){
				catalogo.removeChild(catalogo.childNodes[0]);
				 boton.value="Ver Catalogo";
				
			}
			else{	
		
				if (window.ActiveXObject)
				  {
				  ex=xml.transformNode(xsl);
				  document.getElementById(donde).innerHTML=ex;
				  }
				// code for Mozilla, Firefox, Opera, etc.
				else if (document.implementation && document.implementation.createDocument)
				  {
				  xsltProcessor=new XSLTProcessor();
				  xsltProcessor.importStylesheet(xsl);
				  resultDocument = xsltProcessor.transformToFragment(xml,document);
				  document.getElementById(donde).appendChild(resultDocument);
				 
				  boton.value="Ocultar Catalogo";
				  }
			}//else
		}
				
		//-------------------------------------------------------------------------	
		function aleatorio(inferior,superior){
			numPosibilidades = superior - inferior
			aleat = Math.random() * numPosibilidades
			aleat = Math.floor(aleat)
			return parseInt(inferior) + aleat
		} 

		function colorAleatorio(){
			hexadecimal = new Array("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F")
			color_aleatorio = "#";
			var i=0;
			for (i=0;i<6;i++){
				pos =  Math.ceil(Math.round(Math.random()*15));
				//pos = aleatorio(0,hexadecimal.length)
				color_aleatorio += hexadecimal[pos]
			}
			return color_aleatorio;
		}