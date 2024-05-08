<template lang="pug">
nav.navbar.is-dark(role="navigation", aria-label="main navigation")
  .navbar-brand
    a.navbar-item
      .nav-title _VIVA

.main
  .layout-content
    .box
      .field
        label.label Select
        .control 
          .select 
            select(v-model="search.fieldName")
              option(value="name") Name
              option(value="category") Category
              option(value="bodySection") Body Section
              option(value="Equipment") Equipment
              option(value="secondaryMuscles") Muscle Group
              option(value="chat") Chat

      .field
        label.label Search
        .control
          input.input(type='text', placeholder='Search', v-model="search.value")

      hr
      .buttons
        button.button.is-link(@click="doSearch") Find Workout &nbsp;
          vue-feather(type="search" size="18")
          //- | &nbsp; Find Workout
    
        button.button.is-error(@click="clearSearch") Clear &nbsp;
          vue-feather(type="x", size="18")
          //- span Clear

    .results(v-if="!isLoaded")
      div
        img.overlay(src="../assets/gym_rats.png")
        h1.text(v-if="loading") Lets find you some exerisces...
        h1.text(v-else) Nothing will work unless you do
    .block(v-if="isLoaded")
      strong found {{ exercises.length }} results
    .block
      XyzTransitionGroup(xyz="fade stagger")
        .box(v-if="isLoaded" v-for="exercise in exercises" :key="exercise.id" xyz="inherit down")
          article.media
            .media-left
              figure.image.is-96x96
                img(:src="exercise.pictureLink", alt="Image")
            .media-content
              .content
                strong {{ exercise.name }}
                | &nbsp; 
                small {{ exercise.bodySection }} - {{ exercise.category }}
                p Equipment: {{ exercise.equipments.join(', ') }}
                .tags
                  .padding(v-for="muscle in exercise.primaryMuscles")
                    span.tag.is-primary {{ muscle }}

</template>

<script>
import Typeahead from 'vue3-simple-typeahead';

const baseUrl = 'https://whispering-reef-15102.herokuapp.com';

export default {
  components: {
		Typeahead,
	},
  data() {
    return {
      loading: false,
      isLoaded: false,
      search: {
        fieldName: "name",
        value: ''
      },
      inputEquitpmentValue: '',
      inputMuscleValue: '',
      exInput: {
        id: '',
        category:'',
        bodySection: '',
        name: '',
        equipments: [],
        // primaryMuscles: [],
        secondaryMuscles: [],
        videoLink: '',
        pictureLink: '',
      },
      exercises: [],
      validBodySections: [
        "Full Body",
        "Chest",
        "Shoulder",
        "Back",
        "Arms",
        "Core",
        "Legs"      
      ]
    }
  },
  mounted(){
    this.getExercises();
  },
  methods: {
    getExercises(){
      this.loading = true;
      this.$http.get(`${baseUrl}/exercises/`).then((response) => {
        console.log(response.data);
        this.isLoaded = true;
        this.loading = false;
        this.exercises = response.data;
      });
    },
    doSearch(){
      if(!this.search.value.length) return console.log('missing search value');
      else if(!this.search.fieldName) return console.log('missing search criteria');
      else if(this.search.fieldName && this.search.fieldName === "chat") return this.openSearch();
      else {
        const body = this.search;

        this.$http.post(`${baseUrl}/exercises/search`, body).then((response) => {
          if(response && response.data && response.data.length) this.exercises = response.data;
          else this.$notify({type: "warn", text:"no search results found"});
        }).catch((err) => {
          if(err) {
            console.error(err);
            this.$notify({type:"error", text: err.message});
          }
        })
      }
    },
    openSearch(){
      const body = {
        criteria: this.search.value
      }
      
      this.$http.post(`${baseUrl}/exercises/openSearch`, body).then((response) => {
        
        console.log(response);
      }).catch((err)=>{
        this.$notify({type:"error", text: err.message});
      })
    },
    clearSearch(){
      // this.isloaded = false;
      this.exercises = [];
      this.search.value = "";
      this.getExercises();
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
.layout-content{
  padding: 2rem;
}

.image img {
  height: 100% !important;
  object-fit: cover !important;
}
.padding{
  margin-right: 5px;
}
.nav-title{
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

