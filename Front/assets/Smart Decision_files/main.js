var map, heatmapSeguros, markerClusterSeguros, heatmapCreditos, markerClusterCreditos;

var getGoogleClusterInlineSvg = function (color) {
        var encoded = window.btoa('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="-100 -100 200 200"><defs><g id="a" transform="rotate(45)"><path d="M0 47A47 47 0 0 0 47 0L62 0A62 62 0 0 1 0 62Z" fill-opacity="0.7"/><path d="M0 67A67 67 0 0 0 67 0L81 0A81 81 0 0 1 0 81Z" fill-opacity="0.5"/><path d="M0 86A86 86 0 0 0 86 0L100 0A100 100 0 0 1 0 100Z" fill-opacity="0.3"/></g></defs><g fill="' + color + '"><circle r="42"/><use xlink:href="#a"/><g transform="rotate(120)"><use xlink:href="#a"/></g><g transform="rotate(240)"><use xlink:href="#a"/></g></g></svg>');

        return ('data:image/svg+xml;base64,' + encoded);
    };

var cluster_style_credito = [
        {
            width: 60,
            height: 60,
            url: getGoogleClusterInlineSvg('blue'),
            textColor: 'white',
            textSize: 12
        }
    ];

var cluster_style_seguro = [
        {
            width: 60,
            height: 60,
            url: getGoogleClusterInlineSvg('green'),
            textColor: 'white',
            textSize: 12
        }
    ];

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

function initMap() {
          if (navigator.geolocation) {
             navigator.geolocation.getCurrentPosition(showPosition, showError);

         } else {
             alert("Lo sentimos. Su navegador no permite obtener su localización")
         }

        /*
        heatmap = new google.maps.visualization.HeatmapLayer({
          data: getPointsSeguros(),
          map: map
        });
        */
      }

function changeRadius() {
      // Get the checkbox
  var checkBox = document.getElementById("id-name--2");
  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
  heatmapSeguros.set('radius', heatmapSeguros.get('radius') ? null : 20); 
  }

  checkBox = document.getElementById("id-name--1");
  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
  heatmapCreditos.set('radius', heatmapCreditos.get('radius') ? null : 20);
  } 
    
}

function changeOpacity() {

     var checkBox = document.getElementById("id-name--2");
  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
  heatmapSeguros.set('opacity', heatmapSeguros.get('opacity') ? null : 0.2);
  }

  checkBox = document.getElementById("id-name--1");
  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
  heatmapCreditos.set('opacity', heatmapCreditos.get('opacity') ? null : 0.2);
  }

}

function ajaxrequest(option){
     var urlT;
     if(option == "creditos") urlT = "credit";
     else urlT = "insurance"; 
        $.ajax({
          xhrFields: {
               withCredentials: true
          }, 
          type: "GET",
          url: 'http://167.99.169.199/heatmap/'+urlT,                           
          success: function(data) {
                            console.log('success');
                            var data = JSON.stringify(data);
                            var newArr = JSON.parse(data); 
                            var points = []; 
                            for(i=0; i<newArr.length; i++){
                                   points.push(new google.maps.LatLng(newArr[i].lat, newArr[i].lon));
                              }
                              if(option=="creditos"){
                                   heatmapCreditos = new google.maps.visualization.HeatmapLayer({
                                   data: points
                                 });
                              heatmapCreditos.setMap(map);
                              }else{
                                   heatmapSeguros = new google.maps.visualization.HeatmapLayer({
                                   data: points
                                 });
                              heatmapSeguros.setMap(map);
                              }


                              var markers = points.map(function(location, i) {
                                   return new google.maps.Marker({
                                     position: location
                                   });
                                 });
                                   // Add a marker clusterer to manage the markers.
                                   if(option == "creditos"){
                                        markerClusterCreditos = new MarkerClusterer(map, markers,
                                     {imagePath: '../assets/img.svg', styles: cluster_style_credito});
                                   }else{
                                        markerClusterSeguros = new MarkerClusterer(map, markers,
                                     {imagePath: '../assets/img.svg', styles: cluster_style_seguro});
                                   }
                               
                        }
                    });
}

function mapSeguros(){
     // Get the checkbox
  var checkBox = document.getElementById("id-name--2");
  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
  ajaxrequest("seguros"); 
  } else {
     console.log("vaciar");
     heatmapSeguros.setMap(null);
     markerClusterSeguros.clearMarkers();
  }
}


function mapCreditos(){
     // Get the checkbox
  var checkBox = document.getElementById("id-name--1");
  // If the checkbox is checked, display the output text
  if (checkBox.checked == true){
  ajaxrequest("creditos"); 
  } else {
     console.log("vaciar");
     heatmapCreditos.setMap(null);
     markerClusterCreditos.clearMarkers();
  }
}

function showPosition(position) {

        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 5,
          center: {lat: position.coords.latitude, lng: position.coords.longitude},
          mapTypeId: 'satellite'
        });

        heatmapSeguros = new google.maps.visualization.HeatmapLayer({
          data: []
        });
        heatmapSeguros.setMap(map);
        markerClusterSeguros = new MarkerClusterer(map, [],
                                     {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});

        heatmapCreditos = new google.maps.visualization.HeatmapLayer({
          data: []
        });
        heatmapCreditos.setMap(map);
        markerClusterCreditos = new MarkerClusterer(map, [],
                                     {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = "User denied the request for Geolocation."
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = "Location information is unavailable."
            break;
        case error.TIMEOUT:
            x.innerHTML = "The request to get user location timed out."
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = "An unknown error occurred."
            break;
    }
}
