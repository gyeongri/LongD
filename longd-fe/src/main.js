import './style.css';
import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';
// import VueGoogleMaps from '@fawmi/vue-google-maps';

const app = createApp(App);
// const { VITE_GOOGLE_REST_API_KEY } = import.meta.env;

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core';

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';

/* import specific icons */
import {
  faUserSecret,
  faPhone,
  faPhoneSlash,
  faVolumeXmark,
  faVolumeLow,
  faVideo,
  faVideoSlash,
  faVolumeHigh,
  faRecordVinyl,
  faArrowLeft,
} from '@fortawesome/free-solid-svg-icons';

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

/* add icons to the library */
library.add(
  faUserSecret,
  faPhone,
  faPhoneSlash,
  faVolumeXmark,
  faVolumeLow,
  faVolumeHigh,
  faVideo,
  faVideoSlash,
  faRecordVinyl,
  faArrowLeft,
);

app.use(createPinia());
app.use(router);
app.use(VueSweetalert2);
app.component('font-awesome-icon', FontAwesomeIcon);
// app.use(VueGoogleMaps, {
//   load: {
//     key: 'VITE_GOOGLE_REST_API_KEY',
//   },
// });
//   .mount('#app');
app.mount('#app');
