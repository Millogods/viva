<template lang="pug">
nav.navbar.is-dark(role="navigation", aria-label="main navigation")
  .navbar-brand
    a.navbar-item
      .nav-title SO_FIT
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
          h1 Select Workout
        .column
          p.control.has-icons-left
            input.input(type="text", placeholder="Search")
            span.icon.is-left
              i.fas.fa-search(aria-hidden="true")

    
    .tabs.is-medium
      ul
        li.is-active
          a Resistance
        li
          a Cardio
        li
          a Flexibility
        
    .box
      //- .field
      //-   label.label Name
      //-   .control
      //-     input.input(type="text", placeholder="Text input")
      
      .field
        label.label Body Section
        .control 
          .select 
            select
              option Full Body
              option Upper Body
              option Lower Body
              option Core


      .field.is-grouped.is-grouped-multiline
        label.label Equipment
        .control(v-for="(equipment, idx) in equipmentList")
          .tags.has-addons
            a.tag {{ equipment }}
            a.tag.is-delete(@click="removeEquipmentItem(equipment, idx)")
      .field
        .control
          Typeahead.input(
            id="typeahead_id",
            placeholder="Start writing...",
            :items="equipmentData",
            :minInputLength="1",
            :itemProjection="itemProjectionFunction",
            @selectItem="selectItemEventHandler",
            @onInput="onInputEventHandler",
            @onFocus="onFocusEventHandler",
            @onBlur="onBlurEventHandler"
          )
          //- input.input(type="text", placeholder="Text input")
      hr
      .buttons
        button.button.is-link(@click="getExercise") Find Workout &nbsp;
          vue-feather(type="search" size="18")
          //- | &nbsp; Find Workout
    
        button.button.is-error(@click="clearData") Clear &nbsp;
          vue-feather(type="x", size="18")
          //- span Clear

    .results(v-if="!isLoaded")
      div
        img.overlay(src="../assets/gym_rats.png")
        h1.text(v-if="loading") Lets find you some exerisces...
        h1.text(v-else) Nothing will work unless you do
    .block
      XyzTransitionGroup(xyz="fade stagger")
        .box(v-if="isLoaded" v-for="exercise in data" :key="exercise.id" xyz="inherit down")
          article.media
            .media-left
              figure.image.is-96x96
                img(:src="exercise.media.image", alt="Image")
            .media-content
              .content
                strong {{ exercise.name }}
                //- small @johnsmith
                p {{ exercise.desription }}
              .tags
                .padding(v-for="muscle in exercise.primaryMuscles")
                  span.tag.is-primary {{ muscle }}

              nav.level.is-mobile
                .level-left
                  a.level-item(aria-label="reply") Favorite
                    span.icon
                      vue-feather(type="star" size="18")
                    div &nbsp;|

                  a.level-item(aria-label="reply") Add
                    span.icon
                      vue-feather(type="plus" size="18")
                      div &nbsp;|

                  a.level-item(aria-label="reply") Hide
                    span.icon
                      vue-feather(type="minus-circle" size="18")
</template>

<script>
import Typeahead from 'vue3-simple-typeahead';


export default {
  components: {
		Typeahead,
	},
  data() {
    return {
      data: [
        {
          id: 1,
          name: 'Kettlebell Swing',
          desription: 'A standard kettlebell swing is a great way to strengthen your posterior chain',
          bodySection: 'Full body',
          primaryMuscles: ['Glutes', 'Hamstrings', 'Quadriceps'],
          media: {
            image: 'https://www.muscleandfitness.com/wp-content/uploads/2020/02/DONexerciselibrary-065copy.png',
            video: null
          }
        },
        {
          id: 2,
          name: 'Kettlebell Clean Press',
          desription: 'A standard kettlebell clean press is a great way to tigthen your ass',
          bodySection: 'Full body',
          primaryMuscles: ['Shoulders', 'Triceps', 'Quadriceps'],
          media: {
            image: 'https://www.strongfirst.com/wordpress/wp-content/uploads/2017/04/kettlebell-clean.jpg',
            video: null
          }
        },
        {
          id: 3,
          name: 'Kettlebell Windmill',
          desription: 'A standard kettlebell windwall is a great way to strengthen arms',
          bodySection: 'Full body',
          primaryMuscles: ['Shoulders', 'Triceps', 'Core'],
          media: {
            image: 'https://www.mensjournal.com/.image/c_limit%2Ccs_srgb%2Cq_auto:good%2Cw_700/MTk2MTM3NDI5NDI1ODU4MDUz/kettlebell-swing-form.webp',
            video: null
          }
        }

      
      ],
      equipmentList: ["Kettlebell", "Dumbbell", "Sandbag", "Barbell", "Farmer's Walk Handles", "Box"],
      equipmentData: [
        "Kettlebell", "Dumbbell", "Sandbag", "Barbell", "Farmer's Walk Handles", "Box", "Bench",
        "Reverse Hyperextension machine", "Swiss ball", "Landmine attachment", "Elevated Surface",
        "Ab roller", "Yoga Mat", "Yoga blocks", "Yoga blanket", "Step bench"
      ],
      loading: false,
      isLoaded: false
    }
  },
  mounted(){

  },
  methods: {
    getExercise(){
      
      this.loading = true;
      setTimeout(()=> {
        this.isLoaded = true;
        this.loading = false;
      },2000);
    },
    clearData(){
      // this.isloaded = false;
      this.equipmentList = [];
    },
    selectItemEventHandler(item){
      console.log(item);
      if(!this.equipmentList.includes(item)) this.equipmentList.push(item);
    },
    removeEquipmentItem(item, idx){
      console.log(item);
      console.log(idx);
      this.equipmentList.splice(idx, 1);
    }
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

