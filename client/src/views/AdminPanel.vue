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
        .title.is-4 Admin Panel
    //- .block
    //-   p.control.has-icons-left
    //-     input.input(type="text", placeholder="Search", v-model="searchInput")
    //-     span.icon.is-left
    //-       i.fas.fa-search(aria-hidden="true")

    .block
      .level-item
        .field.has-addons
          p.control
            span.select
              select(v-model="search.fieldName")
                option(value="name") Name
                option(value="category") Category
                option(value="bodySection") Body Section
                option(value="Equipment") Equipment
                option(value="secondaryMuscles") Muscle Group
                option(value="chat") Chat
                
          p.control.is-expanded
            input.input(type='text', placeholder='Search', v-model="search.value")

          p.control
            a.button.is-info(@click="doSearch") Search 
          
          p.control
            a.button.is-text(@click="clearSearch") Clear 

      .field
        .block 
          strong {{ exercises.length }} exercises found
          //- span(v-if="search.value.length" )
          //-  | &nbsp; with execise {{ search.fieldName }} {{'"' + search.value + '""'}}

    .block
      .table-container
        table.table.is-bordered.is-hoverable.is-fullwidth.is-striped
          thead
            tr
              th Id
              th Excersise Name
              th Category
              th Body Section
              th Equitpment
              //- th Primary Muscles
              th Secondary Muscles
              th Image Source
              th Video Source
          tbody
            tr(v-for="(exercise, index) in exercises" :key="index")
              td {{ exercise.id }}

              td {{ exercise.name }}

              td {{ exercise.category }}
                
              td {{ exercise.bodySection }}
                
              td
                div(v-for="equipment in exercise.equipments") {{ equipment }}
                
              //- td {{ exercise.primaryMuscle }}
                
              td
                div(v-for="muscle in exercise.secondaryMuscles") {{ muscle }}
                
              td {{ exercise.pictureLink }}

              td {{ exercise.videoLink }}

      //- nav.pagination(role='navigation', aria-label='pagination')
      //-   a.pagination-previous Previous
      //-   a.pagination-next Next page
      //-   ul.pagination-list
      //-     li
      //-       a.pagination-link(aria-label='Goto page 1') 1
      //-     li
      //-       span.pagination-ellipsis &mldr;
      //-     li
      //-       a.pagination-link(aria-label='Goto page 45') 45
      //-     li
      //-       a.pagination-link.is-current(aria-label='Page 46', aria-current='page') 46
      //-     li
      //-       a.pagination-link(aria-label='Goto page 47') 47
      //-     li
      //-       span.pagination-ellipsis &mldr;
      //-     li
      //-       a.pagination-link(aria-label='Goto page 86') 86
                  
    .block
      .columns
        .column
          .tabs.is-medium
            ul
              li(:class="{'is-active': selectedTab === 'create' }")
                a(@click="selectedTab='create'") Create Exercise
              li(:class="{'is-active': selectedTab === 'update' }")
                a(@click="selectedTab='update'") Update Exercise
              li(:class="{'is-active': selectedTab === 'delete' }")
                a(@click="selectedTab='delete'") Delete
            
          .box
            .field(v-if="selectedTab !== 'create'")
              label.label ID *
                input.input(type="text" placeholder="input id and press return key" v-model.trim="exInput.id", @change="getExercise")

            .field(v-if="selectedTab !== 'delete'")
              .field
                label.label Name *
                  input.input(type="text" v-model="exInput.name")
              
              .field
                label.label Category
                .control 
                  .select 
                    select(v-model="exInput.category")
                      option(value="Resistance training") Resistance
                      option(value="Cardio") Cardio
                      option(value="Flexibility") Flexibility

              .field
                label.label Body Section
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Full Body
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Chest
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Shoulders
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Back
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Arms
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Core
                //- .control
                //-   label.checkbox
                //-     input(type='checkbox' v-model="bodySection")
                //-     | Legs
                .control 
                  .select 
                    select(v-model="exInput.bodySection")
                      option(value="Full Body") Full Body
                      option(value="Chest") Chest
                      option(value="Shoulder") Shoulder
                      option(value="Back") Back
                      option(value="Arms") Arms
                      option(value="Core") Core
                      option(value="Legs") Legs

                  span(v-if="invalidBodySection")
                    p.has-text-danger Invalid Body Section "{{ exInput.bodySection }}" drop down list values only (case sensitive)
                  
              .field
                label.label Equitpment
                .control
                  label.radio
                    input(type='radio' :value="null" v-model="exInput.equipments" @click="clearEquitpment")
                    | None
                .control.has-icons-right
                  input.input.is-clearable(type="text" placeholder="comma separated values" :value="inputEquitpmentValue" @change="updateEquipmentValues")
                  span.icon.is-right
                    button.delete(@click='clearEquitpment')
                  p {{ equipmentValues }}
                  //- div(v-for="item in equipmentValues" :key="item") {{ item }}

              .field
                label.label Muscle Groups
                .control.has-icons-right
                  input.input(type="text" placeholder="comma separated values" :value="inputMuscleValue" @change="updateMuscleValues")
                  span.icon.is-right
                    button.delete(@click='clearMuscles')
                  p {{ muscleValues }}
                //- p(v-for="item in muscleValues" :key="item") {{ item }}

              .field
                label.label Video Link
                  input.input(type="text" v-model="exInput.videoLink")

              .field
                label.label Picture Link
                  input.input(type="text" v-model="exInput.pictureLink")

            .field
              .buttons
                button.button.is-link(v-if="selectedTab === 'create'" @click="createExercise") Create
                button.button.is-link(v-if="selectedTab === 'update'" @click="updateExercise") Update
                button.button.is-link(v-if="selectedTab === 'delete'" @click="deleteExercise") Delete
                a.button.is-text(@click="clearExInput") Clear Input 

          .box
            .field
              label.label Upload Excel Sheet
              .file.has-name
                label.file-label
                  input.file-input(type='file', ref='fileInput', @change='uploadExercises')
                  span.file-cta
                    span.file-icon
                      i.fas.fa-upload
                    span.file-label
                      | Choose a file...
                  span.file-name
                    | {{ file.name || &quot;&quot;}}
            progress.progress(:value='uploadProgress', max='100', v-if='uploadProgress !== null')

          .box
            .field
              label.label Delete All Exercises
              button.button.is-danger(@click="showConfirmDeleteAll = true") Delete All
            .field(v-if="showConfirmDeleteAll")
              strong Are you Sure you want to delete ALL data theres not going back?
              .field
                a(@click="deleteAllExercises") Yes
              .field
                a(@click="showConfirmDeleteAll = false") No


        //- pre {{ exInput }}
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
      search: {
        fieldName: "name",
        value: ''
      },
      showConfirmDeleteAll: false,
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
      selectedTab: 'create',
      exercises: [],
      file: {},
      uploadProgress: null,
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
  computed: {
    equipmentValues() {
      return this.exInput.equipments = this.inputEquitpmentValue.split(',')
      .map(item => item.trim())
      .filter(item => item !== '') || null;
      
    },
    muscleValues() {
      return this.exInput.secondaryMuscles = this.inputMuscleValue.split(',')
      .map(item => item.trim())
      .filter(item => item !== '') || [];
    },
    invalidBodySection() {
      return (this.exInput.bodySection.length && !this.validBodySections.includes(this.exInput.bodySection))
    }
  },
  mounted() {
    this.getExercises();
  },
  methods: {
    getExercise(){
      if(!this.exInput.id && !this.exInput.id.length) return;

      const id = this.exInput.id;
      
      this.$http.get(`${baseUrl}/exercises/${id}`).then((response) => {
        console.log(response.data);
        this.exInput = response.data;
        // this.checkForValidBodySection(re)
        this.updateEquipmentValues(null);
        this.updateMuscleValues(null);
      }).catch((err)=>{
        this.$notify({type:"error", text: err.message});
      })
    },
    getExercises(){
      this.$http.get(`${baseUrl}/exercises/`).then((response) => {
        // console.log(response.data);
        this.exercises = response.data;
      })
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
    uploadExercises() {
      const formData = new FormData();
      formData.append('file', this.$refs.fileInput.files[0]);
      this.file = this.$refs.fileInput.files[0];
      
      const xhr = new XMLHttpRequest();
      
      xhr.upload.addEventListener('progress', (event) => {
        if (event.lengthComputable) {
          this.uploadProgress = Math.round((event.loaded / event.total) * 100);
        }
      });
      
      xhr.addEventListener('load', () => {
        console.log(xhr.responseText);
        this.getExercises();
        this.uploadProgress = null;
      });
      
      xhr.open('POST', `${baseUrl}/exercises/upload`);
      xhr.send(formData);
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
    createExercise(){
      if(!this.exInput.name) return console.log("Need Name")
      delete this.exInput.id;
      const body = this.exInput
      
      this.$http.post(`${baseUrl}/exercises/`, body).then((response) => {
        this.$notify({type:"success", text: `successfully created exercise ${response.data.name}`});
        this.getExercises();
      }).catch((err)=>{
        this.$notify({type:"error", text: err.message});
      })
    },
    updateExercise(){
      const body = this.exInput
      console.log('updateExercise', this.exInput);

      this.$http.patch(`${baseUrl}/exercises/${this.exInput.id}`, body).then((response) => {
        console.log(response);
        this.$notify({type:"success", text: `successfully updated exercise ${response.data.name}`});
        this.getExercises();
      }).catch((err)=>{
        this.$notify({type:"error", text: err.message});
      });
    },
    deleteExercise(){
      if(!this.exInput.id.length) return console.log('Ids is missing');
      const id = this.exInput.id;
      console.log('deleteExercise', id);

      this.$http.delete(`${baseUrl}/exercises/${id}`).then((response) => {
        console.log(response);
        this.$notify({type:"warn", text: response.data});
        this.getExercises();
      }).catch((err)=>{
        console.error(err);
        this.$notify({type:"error", text: err.message});
      });
    },
    deleteAllExercises() {
      const deletePromises = this.exercises.map((exercise) => {
        return this.$http.delete(`${baseUrl}/exercises/${exercise.id}`);
      });

      Promise.all(deletePromises)
        .then(() => {
          this.$notify({ type: "warn", text: "the database has been nuked!" });
          this.showConfirmDeleteAll = false;
          this.getExercises();
        })
        .catch((error) => {
          console.error("An error occurred while deleting exercises:", error);
          this.$notify({ type: "error", text: "An error occurred while deleting exercises" });
        });
    },
    updateEquipmentValues(event) {
      
      if(!event && this.exInput.equipments.length) {
        this.inputEquitpmentValue = this.exInput.equipments.join();
      } else if(event.target.value) this.inputEquitpmentValue = event.target.value;
      if(!this.inputEquitpmentValue.length){
        return this.exInput.equipments = null;
      }
    },
    updateMuscleValues(event) {
      
      if(!event && this.exInput.secondaryMuscles.length) {
        this.inputMuscleValue = this.exInput.secondaryMuscles.join();
      } else if(event.target.value) this.inputMuscleValue = event.target.value;
      if(!this.inputMuscleValue.length){
        return this.exInput.secondaryMuscles = null;
      }
    },
    clearMuscles(){
      this.inputMuscleValue = "";
      this.exInput.secondaryMuscles = [];
    },
    clearEquitpment(){
      this.inputEquitpmentValue = "";
      this.exInput.equipments = null;
    },
    clearSearch() {
      this.search.value = "";
      this.getExercises();
    },
    clearExInput() {
      this.exInput.id = '';
      this.exInput.category = '';
      this.exInput.bodySection = '';
      this.exInput.name = '';
      this.exInput.equipments = [];
      this.exInput.secondaryMuscles = [];
      this.exInput.videoLink = '';
      this.exInput.pictureLink = '';
      this.clearMuscles();
      this.clearEquitpment();
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

.checkbox{
  padding: 1px;
}

</style>
    
    