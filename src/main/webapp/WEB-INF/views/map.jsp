
<!DOCTYPE html>
<html>
<head>
    <title>Mappa Interattiva Comune</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #map { height: 600px; width: 100%; }
        .leaflet-popup-content-wrapper { width: 300px; }
    </style>
</head>
<body>
<h1>Mappa Interattiva Comune</h1>
<div id="map"></div>
<script>
    var map = L.map('map').setView([43.1465, 13.068], 16);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    function loadMarkers() {
        $.getJSON('getMarkers', function(data) {
            data.forEach(function(marker) {
                addMarkerToMap(marker.id, marker.latitude, marker.longitude, marker.title, marker.description, marker.imageUrl);
            });
        });
    }

    function addMarkerToMap(id, lat, lng, title, description, imageUrl) {
        var popupContent = '<b>' + title + '</b><br>' + description;
        if (imageUrl) {
            popupContent += '<br><img src="' + imageUrl + '" alt="Image" style="width:250px;height:auto;">';
        }
        popupContent += '<br><button onclick="deleteMarker(' + id + ', this)">Cancella Contenuto</button>';
        var marker = L.marker([lat, lng]).addTo(map)
            .bindPopup(popupContent);

        marker.on('popupopen', function() {
            $('.leaflet-popup-content button').click(function(event) {
                event.stopPropagation();
            });
        });
    }

    function addMarker(lat, lng, title, description, imageUrl) {
        $.post('addMarker', {
            title: title,
            description: description,
            latitude: lat,
            longitude: lng,
            imageUrl: imageUrl
        }, function(response) {
            map.eachLayer(function (layer) {
                if (!!layer.toGeoJSON) {
                    map.removeLayer(layer);
                }
            });
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
            loadMarkers();
        });
    }

    function deleteMarker(id, button) {
        $.post('deleteMarker', { id: id }, function() {
            var marker = $(button).closest('.leaflet-marker-icon');
            map.removeLayer(marker);
            loadMarkers();
            map.eachLayer(function (layer) {
                if (!!layer.toGeoJSON) {
                    map.removeLayer(layer);
                }
            });
        });
    }

    map.on('click', function(e) {
        var lat = e.latlng.lat;
        var lng = e.latlng.lng;

        var formHtml = '<form id="markerForm">' +
            '<label for="title">Nome:</label><br>' +
            '<input type="text" id="title" name="title" required><br>' +
            '<label for="description">Descrizione:</label><br>' +
            '<textarea id="description" name="description" required></textarea><br>' +
            '<label for="imageUrl">URL Immagine (Facoltativo) :</label><br>' +
            '<input type="text" id="imageUrl" name="imageUrl"><br>' +
            '<button type="submit">Aggiungi Contenuto</button>' +
            '</form>';

        L.popup()
            .setLatLng(e.latlng)
            .setContent(formHtml)
            .openOn(map);

        $('#markerForm').submit(function(event) {
            event.preventDefault();
            var title = $('#title').val();
            var description = $('#description').val();
            var imageUrl = $('#imageUrl').val();
            addMarker(lat, lng, title, description, imageUrl);
            map.closePopup();
        });
    });

    $(document).ready(function() {
        loadMarkers();
    });
</script>
</body>
</html>