<script setup lang="ts">

import {ref,onMounted} from 'vue'
import {taskService} from "@/api/taskService"
import type { Task } from './types'

const tasks = ref<Task[]>([])
const loading = ref<boolean>(true)
const error = ref<string | null>(null)

async function loadTasks(): Promise<void> {
  try{
    loading.value = true
    const response = await taskService.getAll()
    tasks.value = response.data
}catch(err) {
    error.value = "loading error"
    console.error(err)
  }finally {
    loading.value = false
  }
}
onMounted(()=>{
  loadTasks()
})

const newTask = ref<Omit<Task,'id'>>({
  title : '',
  description : '',
  priority : 0,
  day : 0,
  month : 0,
  year : 0
})

async function addTask () : Promise < void > {
  try {
    // TODO : Appeler le service pour creer le produit
    await taskService.create( newTask.value)

    // TODO : Recharger la liste des tâches
    await loadTasks();
    // Reinitialiser le formulaire
    newTask.value = { title : '', description : '', priority : 0, year : 0, month : 0, day : 0 }
  } catch ( err ) {
    error . value = 'Erreur lors de l\'ajout '
  }
}

async function deleteTask( id : number ) : Promise < void > {
  if ( confirm (' Supprimer ce produit ? ') ) {
    try {
      await taskService.delete( id ) // TODO : Quelle methode ?
      await loadTasks()
      } catch ( err ) {
      error . value = ' Erreur lors de la suppression '
      }
    }
  }

const taskInEdition = ref < Task | null >( null )
function editTask ( task : Task ): void {
  taskInEdition.value = { ... task }
  }
function cancelEdit () : void {
  taskInEdition . value = null
  }

 async function saveTask() : Promise < void > {
   if (!taskInEdition.value) return
   try {
     const id = taskInEdition.value.id
     await taskService.update( id , taskInEdition . value )
     await loadTasks()
     taskInEdition . value = null
     } catch ( err ) {
     error . value = ' Erreur lors de la modification '
    }
   }
</script>

<template>
  <div>
  <!-- Afficher pendant le chargement -->
  <p v-if ="loading"> Loading ... </p >

  <!-- Afficher en cas d ' erreur -->
  <p v-else-if =" error " class =" error "> {{ error }} </p >
    <!-- Formulaire d ' edition ( affiche si produitEnEdition ) -->

    <div v-else-if="taskInEdition" class =" modal ">
      <h2> Update the task </h2 >
      <input v-model="taskInEdition.title " placeholder ="Title" required/>
      <input v-model ="taskInEdition.description" placeholder =" Description" />
      <input v-model.number =" taskInEdition.priority " type ="number" placeholder =" Priority " />
      <input v-model.number =" taskInEdition.day " type ="number" placeholder =" Day " />
      <input v-model.number =" taskInEdition.month " type ="number" placeholder =" Month " />
      <input v-model.number =" taskInEdition.year " type ="number" placeholder =" Year " />
      <button @click =" saveTask " > Save </button >
      <button @click =" cancelEdit " > Cancel </button >
    </div>

    <!-- Dans la carte produit , ajouter le bouton Modifier -->

  <div v-else class="tasks">
    <div v-for="task in tasks" :key ="task.id" class ="task-card">
      <h3>{{ task.title }}</h3>
      <p>{{ task.description }}</p>
      <p class = date>{{task.day}}/{{task.month}}/{{task.year}}</p>
      <p class = priority>{{task.priority}}</p>
      <button @click ="deleteTask( task.id )" class =" btn-supprimer">
        Delete
      </button>
      <button @click =" editTask ( task ) "> Modifier </button >
  </div>
  </div>
    <!-- Formulaire d ' ajout -->
    <form @submit.prevent="addTask" class ="form-ajout" >
    <h2> Add  </h2>
    <input v-model="newTask.title " placeholder ="Title" required/>
    <input v-model ="newTask.description" placeholder ="Description"/>
    <input v-model.number =" newTask.priority " type ="number" placeholder =" Priority " />
    <input v-model.number =" newTask.day " type ="number" placeholder =" Day " />
      <input v-model.number =" newTask.month " type ="number" placeholder =" Month " />
      <input v-model.number =" newTask.year " type ="number" placeholder =" Year " />
    <button type ="submit"> Ajouter </button>
  </form >
  </div>
</template>

<style lang="scss">
@use '../../../task_planner/Frontend/src/assets/scss/base' as *;
@use '../../../task_planner/Frontend/src/assets/scss/debug' as *;

.tasks { display : flex ; flex-wrap : wrap ; gap : 20px ; }
.task-card { border : 1px solid #ddd ; padding : 15px ;border-radius : 8px ; width : 250px ; }
.prix { font-weight : bold ; color : #42b883 ; font-size : 1.2em ; }
.error { color : red ; }
.header {
  grid-area: header;
}
.shop {
  grid-area: shop;
}
.cart {
  grid-area: cart;
  border-left: var(--border);
  background-color: white;
}
.footer {
  grid-area: footer;
}
</style>
