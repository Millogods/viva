<template lang="pug">
nav.navbar.is-dark(role="navigation", aria-label="main navigation")
  .navbar-brand
    a.navbar-item
      .nav-title _VIVA
    //- img(src="https://bulma.io/images/bulma-logo.png", alt="", width="112", height="28")
  //- a.navbar-burger(role="button", aria-label="menu", aria-expanded="false")
  //-   span(aria-hidden="true")
  //-   span(aria-hidden="true")
  //-   span(aria-hidden="true")
.main
  .layout-content
    .block
      .columns
        h1 Admin Panel
    .block
      .columns
        .column
          table.table.is-bordered.is-hoverable.is-fullwidth
            thead
              tr
                th Id
                th Excersise Name
                th Category
                th Body Section
                th Equitpment
                th Primary Muscles
                th Secondary Muscles
                th Image Source
                //- th Video Source
            tbody
              tr(v-for="(exercise, index) in exercises" :key="index")
                td {{ exercise.id }}

                td {{ exercise.name }}

                td {{ exercise.category }}
                  
                td {{ exercise.bodySection }}
                  
                td {{ exercise.equipment }}
                  
                td {{ exercise.primaryMuscle }}
                  
                td {{ exercise.secondaryMuscle }}
                  
                td {{ exercise.pictureLink }}

                //- td {{ exercise.videoLink }}
                  

    .block
      .columns
        .column
          .box
            .h1 Create Exersice
            .field
              label.label Category 
              .control 
                .select 
                  select
                    option Resistance
                    option Cardio
                    option Flexibility
                  
              label.label Body Section
              .control 
                .select 
                  select
                    option Full Body
                    option Upper Body
                    option Lower Body
                    option Core

            .field
              label.label Name *
                input.input(type="text" v-model="name")
            
              label.label Equitpment
                input.input(type="text" v-model="equipment")

            .buttons
              button.button.is-link(@click="createExercises") Create

    </template>
    
<script>
import Typeahead from 'vue3-simple-typeahead';


export default {
  components: {
    Typeahead,
  },
  data() {
    return {
      exercises: [],
      createInput:{
        category:'',
        bodySection: '',
        name: '',
        equipment: '',
        primaryMuscle: '',
        secondaryMuscle: '',
      }
    }
    
  },
  mounted() {
    this.getExercises();
  },
  methods: {
    getExercises(){
      this.$http.get('https://whispering-reef-15102.herokuapp.com/exercises/').then((response) => {
        console.log(response.data);
        this.exercises = response.data;
      })
    },
    createExercises(){
      const body = {
        bodySection: 'lower body',
        name: 'new test 12',
        // type:this.type //this doesnt work and isnt needed
        equipment:'bells',
        primaryMuscle:'primaryMusclesTest2',
        secondaryMuscle:'secondaryMusclesTest2',
        category: 'core',
        pictureLink: "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"

      }

      console.log('new input',body);
      this.$http.post('https://whispering-reef-15102.herokuapp.com/exercises/', body).then((response) => {
        console.log(response);
        this.getExercises();
      })
    },
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

</style>
    
    