<template lang="pug">
nav.navbar.is-dark(role="navigation", aria-label="main navigation")
.navbar-brand
  a.navbar-item
    .nav-title _VIVA
    //- img(src="https://bulma.io/images/bulma-logo.png", alt="", width="112", height="28")
  a.navbar-burger(role="button", aria-label="menu", aria-expanded="false")
    span(aria-hidden="true")
    span(aria-hidden="true")
    span(aria-hidden="true")
.main
  .layout-content
    .block
      .columns
        .column
          h1 Admin Panel
        .column
        table.table.is-bordered.is-hoverable.is-fullwidth
          thead
            tr
              th Category
              th Body Section
              th Equipment Name
              th Primary Muscles
              th Secondary Muscles
              th Exercise ID
              th Exercise Type
              th Video Source
              th Image Source
          tbody
            tr(v-for="(exercise, index) in exercises" :key="index")
              td
                input(type="text" v-model="exercise.category")
              td
                input(type="text" v-model="exercise.bodySection")
              td
                input(type="text" v-model="exercise.equipmentName")
              td
                input(type="text" v-model="exercise.primaryMuscles")
              td
                input(type="text" v-model="exercise.secondaryMuscles")
              td
                input(type="text" v-model="exercise.exerciseId")
              td
                input(type="text" v-model="exercise.exerciseType")
              td
                input(type="text" v-model="exercise.videoSource")
              td
                input(type="text" v-model="exercise.imageSource")

    </template>
    
<script>
import Typeahead from 'vue3-simple-typeahead';


export default {
  components: {
    Typeahead,
  },
  data() {
    return {
      data() {
    return {
      exercises: [
        {
          category: '',
          bodySection: '',
          equipmentName: '',
          primaryMuscles: '',
          secondaryMuscles: '',
          exerciseId: '',
          exerciseType: '',
          videoSource: '',
          imageSource: '',
        },
      ],
    };
  },
    }
  },
  mounted() {

  },
  methods: {
    //
  }
}
</script>
    
<style scoped>
.main {
  /* margin: 2rem; */
  background-color: #eff3f8;
  height: 100vh;
  width: 100vw;
}

.layout-content {
  padding: 2rem;
}

.image img {
  height: 100% !important;
  object-fit: cover !important;
}

.padding {
  margin-right: 5px;
}

.nav-title {
  color: #f3f3f3;
  font-size: 2rem;
  font-weight: 600;
  line-height: 1.125;
  font-family: sans-serif;
  font-style: inherit;
  letter-spacing: .5rem;
}

.results {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 600px;
  width: 100%;
  margin: 0;
}

.results .text {
  display: flex;
  text-align: center;
  justify-content: center;
  color: gray;
}

.overlay {
  display: flex;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
  opacity: 0.4;
  filter: grayscale(100%);
}

.level-left a {
  color: rgb(177, 177, 177);
}

.level-left a:hover {
  color: rgb(112, 112, 252);
}
</style>
    
    