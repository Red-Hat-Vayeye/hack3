var map, heatmap, markerCluster;

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
    heatmap.set('radius', heatmap.get('radius') ? null : 20);
}

function changeOpacity() {
	heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
}

function ajaxrequest(option){
     map.setZoom(6);
     heatmap.setMap(null);
     markerCluster.clearMarkers();
        $.ajax({
          type: "GET",
          url: 'http://172.20.10.2:8081/'+option,                           
          success: function(data) {
                            console.log('success');
                            var data = JSON.stringify(data);
                            var newArr = JSON.parse(data); 
                            var points = []; 
                            for(i=0; i<newArr.length; i++){
                                   points.push(new google.maps.LatLng(newArr[i].lat, newArr[i].lon));
                              }

                              heatmap = new google.maps.visualization.HeatmapLayer({
                                   data: points
                                 });
                              heatmap.setMap(map);


                              var markers = points.map(function(location, i) {
                                   return new google.maps.Marker({
                                     position: location
                                   });
                                 });
                                   // Add a marker clusterer to manage the markers.
                                   if(option == "creditos"){
                                        markerCluster = new MarkerClusterer(map, markers,
                                     {imagePath: '../assets/img.svg', styles: cluster_style_credito});
                                   }else{
                                        markerCluster = new MarkerClusterer(map, markers,
                                     {imagePath: '../assets/img.svg', styles: cluster_style_seguro});
                                   }
                               
                        }
                    });
}

function mapSeguros(){
     ajaxrequest("seguros");
}


function mapCreditos(){
     ajaxrequest("creditos");
}

function showPosition(position) {

        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 5,
          center: {lat: position.coords.latitude, lng: position.coords.longitude},
          mapTypeId: 'satellite'
        });

        heatmap = new google.maps.visualization.HeatmapLayer({
          data: []
        });
        heatmap.setMap(map);
        markerCluster = new MarkerClusterer(map, [],
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


function getPointsSeguros() {
        return [
          new google.maps.LatLng(37.782551, -122.445368),
          new google.maps.LatLng(37.782745, -122.444586),
          new google.maps.LatLng(37.782842, -122.443688),
          new google.maps.LatLng(37.782919, -122.442815),
          new google.maps.LatLng(37.782992, -122.442112),
          new google.maps.LatLng(37.783100, -122.441461),
          new google.maps.LatLng(37.783206, -122.440829),
          new google.maps.LatLng(37.765543, -122.413039),
          new google.maps.LatLng(37.765532, -122.413125),
          new google.maps.LatLng(37.765500, -122.413553),
          new google.maps.LatLng(37.765448, -122.414053),
          new google.maps.LatLng(37.765388, -122.414645),
          new google.maps.LatLng(37.765323, -122.415250),
          new google.maps.LatLng(37.765303, -122.415847),
          new google.maps.LatLng(37.765251, -122.416439),
          new google.maps.LatLng(37.765204, -122.417020),
          new google.maps.LatLng(37.765172, -122.417556),
          new google.maps.LatLng(37.765164, -122.418075),
          new google.maps.LatLng(37.765153, -122.418618),
          new google.maps.LatLng(37.765136, -122.419112),
          new google.maps.LatLng(37.765129, -122.419378),
          new google.maps.LatLng(37.765119, -122.419481),
          new google.maps.LatLng(37.765100, -122.419852),
          new google.maps.LatLng(37.765083, -122.420349),
          new google.maps.LatLng(37.765045, -122.420930),
          new google.maps.LatLng(37.764992, -122.421481),
          new google.maps.LatLng(37.764980, -122.421695),
          new google.maps.LatLng(37.764993, -122.421843),
          new google.maps.LatLng(37.764986, -122.422255),
          new google.maps.LatLng(37.764975, -122.422823),
          new google.maps.LatLng(37.764939, -122.423411),
          new google.maps.LatLng(37.764902, -122.424014),
          new google.maps.LatLng(37.764853, -122.424576),
          new google.maps.LatLng(37.764826, -122.424922),
          new google.maps.LatLng(37.764796, -122.425375),
          new google.maps.LatLng(37.764782, -122.425869),
          new google.maps.LatLng(37.764768, -122.426089),
          new google.maps.LatLng(37.764766, -122.426117),
          new google.maps.LatLng(37.764723, -122.426276),
          new google.maps.LatLng(37.764681, -122.426649),
          new google.maps.LatLng(37.782012, -122.404200),
          new google.maps.LatLng(37.781574, -122.404911),
          new google.maps.LatLng(37.781055, -122.405597),
          new google.maps.LatLng(37.780479, -122.406341),
          new google.maps.LatLng(37.779996, -122.406939),
          new google.maps.LatLng(37.779459, -122.407613),
          new google.maps.LatLng(37.778953, -122.408228),
          new google.maps.LatLng(37.778409, -122.408839),
          new google.maps.LatLng(37.777842, -122.409501),
          new google.maps.LatLng(37.777334, -122.410181),
          new google.maps.LatLng(37.776809, -122.410836),
          new google.maps.LatLng(37.776240, -122.411514),
          new google.maps.LatLng(37.775725, -122.412145),
          new google.maps.LatLng(37.775190, -122.412805),
          new google.maps.LatLng(37.774672, -122.413464),
          new google.maps.LatLng(37.774084, -122.414186),
          new google.maps.LatLng(37.773533, -122.413636),
          new google.maps.LatLng(37.773021, -122.413009),
          new google.maps.LatLng(37.772501, -122.412371),
          new google.maps.LatLng(37.771964, -122.411681),
          new google.maps.LatLng(37.771479, -122.411078),
          new google.maps.LatLng(37.770992, -122.410477),
          new google.maps.LatLng(37.770467, -122.409801),
          new google.maps.LatLng(37.770090, -122.408904),
          new google.maps.LatLng(37.769657, -122.408103),
          new google.maps.LatLng(37.769132, -122.407276),
          new google.maps.LatLng(37.768564, -122.406469),
          new google.maps.LatLng(37.767980, -122.405745),
          new google.maps.LatLng(37.767380, -122.405299),
          new google.maps.LatLng(37.766604, -122.405297),
          new google.maps.LatLng(37.765838, -122.405200),
          new google.maps.LatLng(37.765139, -122.405139),
          new google.maps.LatLng(37.764457, -122.405094),
          new google.maps.LatLng(37.763716, -122.405142),
          new google.maps.LatLng(37.762932, -122.405398),
          new google.maps.LatLng(37.762126, -122.405813),
          new google.maps.LatLng(37.761344, -122.406215),
          new google.maps.LatLng(37.760556, -122.406495),
          new google.maps.LatLng(37.759732, -122.406484),
          new google.maps.LatLng(37.758910, -122.406228),
          new google.maps.LatLng(37.758182, -122.405695),
          new google.maps.LatLng(37.757676, -122.405118),
          new google.maps.LatLng(37.757039, -122.404346),
          new google.maps.LatLng(37.756335, -122.403719),
          new google.maps.LatLng(37.755503, -122.403406),
          new google.maps.LatLng(37.754665, -122.403242),
          new google.maps.LatLng(37.753837, -122.403172),
          new google.maps.LatLng(37.752986, -122.403112),
          new google.maps.LatLng(37.751266, -122.403355)
        ];
      }

/*
function myMap() {
var mapProp= {
    center:new google.maps.LatLng(51.508742,-0.120850),
    zoom:5,
};
var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
}
*/