<template>
  <div class="container">
    <h1>TODO List</h1>
    <div>
      <b-button variant="primary" v-b-modal.modal-prevent-closing>추가</b-button>
      <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="Submit Your Task"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk">
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group :state="titleState" label="Title" label-for="title-input" invalid-feedback="Title is required">
          <b-form-input ref="title" id="title-input" v-model="title" :state="titleState" required>
          </b-form-input>
        </b-form-group>
          <b-form-group :state="contentState" label="Content" label-for="content-input" invalid-feedback="Content is required">
            <b-form-input ref="content" id="content-input" v-model="content" :state="contentState" required>
            </b-form-input>
        </b-form-group>
      </form>
    </b-modal>
    </div>
    <br>
      <div class="todos-info">
        <span>Total : {{ todos.length }}, </span>
        <span>Complete : {{ selected }}</span>
      </div>
    <br>
    <div class="todo-table">
      <b-table :items="todos" :fields="fields" >
        <template slot="priority" slot-scope="row">
          <div >
            <star-rating v-model="row.item.priority" :star-size="20" :show-rating="false" @rating-selected="setPriority(row.item)"></star-rating>
          </div>
        </template>

        <template slot="title" slot-scope="row">
          <h5 v-bind:class="{completed: row.item.completed}" style="font-weight:bold">{{row.item.title}}</h5>
        </template>

        <template slot="content" slot-scope="row">
          <h5 v-bind:class="{completed: row.item.completed}">{{row.item.content}}</h5>
        </template>

        <template slot="modify" slot-scope="row">
        <b-button size="sm" variant="outline-primary" @click="row.toggleDetails" class="mr-2">
          {{ row.detailsShowing ? '취소' : '수정'}}
        </b-button>
        </template>

        <template slot="complete" slot-scope="row" >
        <b-button size="sm" variant="outline-success" @click="completeTodo(row.item)">완료</b-button>
        </template>

        <template slot="row-details" slot-scope="row">
          <b-card>
            <b-form-group :state="titleState" label="Title" label-for="m-title-input" invalid-feedback="Title is required">
              <b-form-input ref="mtitle" id="m-title-input" v-model="row.item.title" :state="titleState" required>
              </b-form-input>
            </b-form-group>
              <b-form-group :state="contentState" label="Content" label-for="m-content-input" invalid-feedback="Content is required">
                <b-form-input ref="mcontent" id="m-content-input" v-model="row.item.content" :state="contentState" required>
                </b-form-input>
            </b-form-group>
            <b-button size="sm" variant="outline-primary" @click="handleModifySubmit(row.item)">수정</b-button>
          </b-card>
        </template>

        <template slot="delete" slot-scope="row">
          <div class="delete-btn">
            <b-button size="sm" variant="outline-danger" @click="deleteTodo(row.item)">삭제</b-button>
          </div>
        </template>

        <template slot="date" slot-scope="row">
          <datetime type="date" v-model="row.item.date" placeholder="Pick a day">
            <template slot="button-confirm">
                <b-button size="sm" variant="outline-primary" @click="getDate(row.item)">OK</b-button>
            </template>
          </datetime>
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
import 'vue-datetime/dist/vue-datetime.css'
import axios from 'axios'
import moment from 'moment'

export default {
  name: 'TodoList',
  data () {
    return {
      id: 1,
      completed: false,
      selected: 0,
      response: [],
      errors: [],
      date: null,
      title: '',
      titleState: null,
      content: '',
      contentState: null,
      todos: [],
      length: 0,
      priority: 0,
      fields: [
        {
          key: 'priority',
          label: 'Priority',
          sortable: true
        },
        {
          key: 'title',
          label: 'Title'
        },
        {
          key: 'content',
          label: 'Content'
        },
        'complete',
        'modify',
        'delete',
        {
          key: 'date',
          label: 'Date'
        }
      ]
    }
  },
  methods: {
    setPriority (item) {
      axios.put(`http://localhost:8080/${item.id}/priority`, {
        id: item.id,
        title: item.title,
        content: item.content,
        priority: item.priority,
        date: item.date,
        completed: item.completed
      })
        .then(response => {
          this.response = response.data
          console.log(response.data)
          this.getTodos()
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    getDate (item) {
      if (item.date === '') {
        console.log(moment().format('YYYYMMDD'))
      }
      console.log(moment(item.date).format('YYYYMMDD'))
      axios.put(`http://localhost:8080/todos/${item.id}/priority`, {
        id: item.id,
        title: item.title,
        content: item.content,
        priority: item.priority,
        date: item.date,
        completed: item.completed
      })
        .then(response => {
          this.response = response.data
          console.log(response.data)
          // this.getTodos()
        })
        .catch(e => {
          this.errors.push(e)
        })

      // console.log(item.date)
      // var variable = moment()
      // var now = moment(variable).format('YYYYMMDD')
      // var date = moment(item.date).format('YYYYMMDD')
      // if (moment(now).diff(date) <= 0) {
      //   // return true
      // } else {
      //   // item._rowVariant = 'danger'
      //   // return false
      // }
      // return moment(date).format('YYYYMMDD')
    },
    completeTodo (item) {
      axios.put(`http://localhost:8080/todos/${item.id}/completed`, {
        id: item.id,
        title: item.title,
        content: item.content,
        priority: item.priority,
        date: item.date,
        completed: item.completed
      })
        .then(response => {
          this.response = response.data
          console.log(response.data)
          this.selected++
          this.getTodos()
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    deleteTodo (item) {
      axios.get(`http://localhost:8080/delete/todos/${item.id}`)
        .then(response => {
          this.response = response.data
          console.log(response.data)
          alert('삭제되었습니다.')
          this.getTodos()

          if (item.completed) {
            this.selected = this.selected - 1
          }
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    checkTitleFormValidity () {
      const valid = this.$refs.title.checkValidity()
      this.titleState = valid ? 'valid' : 'invalid'
      return valid
    },
    checkContentFormValidity () {
      const valid = this.$refs.content.checkValidity()
      this.contentState = valid ? 'valid' : 'invalid'
      return valid
    },
    checkModifyTitleFormValidity () {
      const valid = this.$refs.mtitle.checkValidity()
      this.titleState = valid ? 'valid' : 'invalid'
      return valid
    },
    checkModifyContentFormValidity () {
      const valid = this.$refs.mcontent.checkValidity()
      this.contentState = valid ? 'valid' : 'invalid'
      return valid
    },
    resetModal () {
      this.id = this.id
      this.title = ''
      this.titleState = null
      this.content = ''
      this.contentState = null
      this.priority = 1
      this.date = null
    },
    handleOk (bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault()
      // Trigger submit handler
      this.handleSubmit()
    },
    handleSubmit () {
      // Exit when the form isn't valid
      if (!this.checkTitleFormValidity() && !this.checkContentFormValidity()) {
        return
      } else if (!this.checkTitleFormValidity()) {
        return
      } else if (!this.checkContentFormValidity()) {
        return
      }

      axios.post(`http://localhost:8080/todos`, {
        id: this.id,
        title: this.title,
        content: this.content,
        priority: this.priority,
        date: this.date,
        completed: this.completed
      })
        .then(response => {
          this.response = response.data
          console.log(response.data)
          this.id++
          this.getTodos()
        })
        .catch(e => {
          this.errors.push(e)
        })

      // Hide the modal manually
      this.$nextTick(() => {
        this.$refs.modal.hide()
      })
    },
    handleModifySubmit (item) {
      // Exit when the form isn't valid
      if (!this.checkModifyTitleFormValidity() && !this.checkModifyContentFormValidity()) {
        return
      } else if (!this.checkModifyTitleFormValidity()) {
        return
      } else if (!this.checkModifyContentFormValidity()) {
        return
      }

      axios.put(`http://localhost:8080/todos/${item.id}`, {
        id: item.id,
        title: item.title,
        content: item.content,
        priority: item.priority,
        date: item.date,
        completed: item.completed
      })
        .then(response => {
          this.response = response.data
          console.log(response.data)
          this.getTodos()
        })
        .catch(e => {
          this.errors.push(e)
        })

      // Hide the modal manually
      this.$nextTick(() => {
        this.$refs.modifymodal.hide()
      })
    },
    getTodos () {
      axios.get('http://localhost:8080/list')
        .then(response => {
          this.response = response.data
          this.todos = response.data
          console.log(response.data)
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  },
  created () {
    this.getTodos()
    console.log(this.todos.length)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.completed {
  text-decoration: line-through;
}
.delete-btn{
}
h2 {
  font-weight: normal;
}
</style>
