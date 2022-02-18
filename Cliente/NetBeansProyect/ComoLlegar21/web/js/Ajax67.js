/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//----------------------------------------------
function enviarPeticion(metodo,url,asincrono,procesaRespuesta,datos){
    
    var xhttp = creaPeticion();
    
    xhttp.onreadystatechange=function() {
    //    console.log(this.status)
    //if (this.readyState == 4 && this.status == 200) {
      procesaRespuesta(this);
    //}
    };
    
    if(metodo=="GET"){
        xhttp.open(metodo,url+"?"+datos,asincrono); 
        
        xhttp.send();
    }
    else{
       xhttp.open(metodo,url,asincrono); 
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
       xhttp.send(datos);  
    }
    
}

//------------------------------------------------
function creaPeticion(){
    if (window.XMLHttpRequest) {
        // code for modern browsers
        xmlhttp = new XMLHttpRequest();
     } else {
        // code for old IE browsers
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}
//--------------------------------------------------------------
function loadDocXML(fichero){
      
  var xhttp = creaPeticion();
 /* 
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) 
        return this.responseXML;
    else
        alert("readyState = "+this.readyState+
             "status = "+this.status);
   };*/
   
  xhttp.open("GET", fichero, false);
  xhttp.send();
  
  return xhttp.responseXML;
} 
//-----------------------------------------------------------------
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
			
		 function Cargar(id){
			 	
				xmlDoc=loadDocXML("xml/cdcatalog.xml");
				xslDoc=loadDocXML("xml/cdcatalog.xsl");
				//cds= xml.getElementsByTagName("cd");
				
				var lista=document.getElementById(id);
				
				tit=xmlDoc.getElementsByTagName("title");
				aut=xmlDoc.getElementsByTagName("artist");
				cou=xmlDoc.getElementsByTagName("country");
				pre=xmlDoc.getElementsByTagName("price");
				//alert("tit.length = " + tit.length);
				
				for (i=0;i<tit.length;i++)
				  {
				  lista.options.add(new Option( tit[i].childNodes[0].nodeValue +" / "+
				  								aut[i].childNodes[0].nodeValue,
												pre[i].childNodes[0].nodeValue ) );
				
				   linea = new lineacd(tit[i].childNodes[0].nodeValue,
									aut[i].childNodes[0].nodeValue,
									cou[i].childNodes[0].nodeValue,
									pre[i].childNodes[0].nodeValue,
									colorAleatorio());
									
				 	listaCds[Object.keys(listaCds).length]=linea;								
												
				  }
				//crear objeto y llamar a tabla2......................
		
	 // tabla2("lista de CD's","carro","catalogo",listaCds,false);				
			}
//-----------------------------------------------------------
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