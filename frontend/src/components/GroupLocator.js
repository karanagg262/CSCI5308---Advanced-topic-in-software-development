import React from 'react';
import { MapContainer, Marker, Popup, TileLayer } from 'react-leaflet';
import axios from 'axios';

class GroupLocator extends React.Component {
  constructor() {
    super();
    this.state = {
      lat: 44.651070,
      lng: -63.582687,
      zoom: 13,
      markers: [],
      isLoading: true,
    };
  }
  async componentDidMount() {
    const config = {
        method: 'post',
        url: 'http://localhost:8080/location',
        headers: { }
      };
      await axios(config)
      .then(response => this.setState({ markers: response.data, isLoading: false }))
      .catch(err=> console.log(err.response.data));
      console.log(this.state.markers);
  }
  render() {
    const position = [this.state.lat, this.state.lng];
    return (
      <div className='map'>
      <MapContainer center={position} zoom={this.state.zoom} style={{ height: 400, width: "100%", zIndex: 94 }}>
        <TileLayer
          attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url='http://{s}.tile.osm.org/{z}/{x}/{y}.png'
        />
        {this.state.markers.map((marker,index) => (
          <Marker key={index} position={[marker.latitude, marker.longitude]}>
            <Popup>
              <span>
                A pretty CSS3 popup. <br /> Easily customizable.
              </span>
            </Popup>
          </Marker>
        ))}
      </MapContainer>
      </div>
    );
  }
}
export default GroupLocator;