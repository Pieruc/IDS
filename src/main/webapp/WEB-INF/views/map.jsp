<!DOCTYPE html>
<html>
<head>
    <title>Leaflet Map with H2 Database</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <style>
        #map {
            height: 600px;
            width: 100%;
        }
        #formContainer {
            display: none;
            position: absolute;
            top: 20px;
            left: 20px;
            background: white;
            padding: 20px;
            border: 1px solid #ccc;
            z-index: 1000;
        }
    </style>
</head>
<body>
<h1>Interactive Map with H2 Database</h1>
<div id="map"></div>
<div id="formContainer">
    <h3>Add Marker</h3>
    <form id="popupForm">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea><br><br>
        <label for="imageUrl">Image URL:</label>
        <input type="text" id="imageUrl" name="imageUrl"><br><br>
        <button type="submit">Add Marker</button>
        <button type="button" id="cancelButton">Cancel</button>
    </form>
</div>

<script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
<script>
    var map = L.map('map').setView([51.505, -0.09], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    var formContainer = document.getElementById('formContainer');
    var popupForm = document.getElementById('popupForm');
    var cancelButton = document.getElementById('cancelButton');
    var currentLatLng;

    map.on('click', function(e) {
        currentLatLng = e.latlng;
        formContainer.style.display = 'block';
    });

    popupForm.addEventListener('submit', function(e) {
        e.preventDefault();
        var title = document.getElementById('title').value;
        var description = document.getElementById('description').value;
        var imageUrl = document.getElementById('imageUrl').value;

        var popupContent = "<div>" +
            "<h3>" + title + "</h3>" +
            "<p>" + description + "</p>";

        if (imageUrl) {
            popupContent += '<img src="' + imageUrl + '" alt="Image" style="width: 100%; height: auto;" />';
        }

        popupContent += "</div>";

        var marker = L.marker(currentLatLng).addTo(map)
            .bindPopup(popupContent).openPopup();

        // Salva il marker nel database
        var markerData = {
            latitude: currentLatLng.lat,
            longitude: currentLatLng.lng,
            title: title,
            description: description,
            imageUrl: imageUrl
        };

        fetch('/markers', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(markerData)
        }).then(response => response.json())
            .then(data => console.log('Marker saved:', data))
            .catch(error => console.error('Error:', error));

        formContainer.style.display = 'none';
        popupForm.reset();
    });

    cancelButton.addEventListener('click', function() {
        formContainer.style.display = 'none';
        popupForm.reset();
    });

    // Carica i marker dal database
    fetch('/markers')
        .then(response => response.json())
        .then(markers => {
            markers.forEach(marker => {
                var popupContent = "<div>" +
                    "<h3>" + marker.title + "</h3>" +
                    "<p>" + marker.description + "</p>";

                if (marker.imageUrl) {
                    popupContent += '<img src="' + marker.imageUrl + '" alt="Image" style="width: 100%; height: auto;" />';
                }

                popupContent += "</div>";

                L.marker([marker.latitude, marker.longitude]).addTo(map)
                    .bindPopup(popupContent);
            });
        })
        .catch(error => console.error('Error:', error));
</script>
</body>
</html>
