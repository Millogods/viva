import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueFeather from 'vue-feather';
import Typeahead from 'vue3-simple-typeahead';
import VueAnimXyz from '@animxyz/vue3'
import '@animxyz/core' // Import css here if you haven't elsewhere

// import 'bulma/css/bulma.css';

const app = createApp(App)
app.use(VueAnimXyz)
app.use(router)
app.use(Typeahead);
app.component(VueFeather.name, VueFeather);

app.mount('#app')
