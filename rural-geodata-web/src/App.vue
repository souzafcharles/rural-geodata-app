<template>
  <div id="app">

    <nav>
      <div class="nav-wrapper green darken-2">
        <a href="#" class="brand-logo center">
          <i class="material-icons left">landscape</i>
          Rural GeoData App
        </a>
      </div>
    </nav>

    <div class="container">

      <!-- Mensagens de Erro Gerais -->
      <div v-if="backendErrors.length > 0" class="card-panel red lighten-4">
        <h6>Erros do Servidor:</h6>
        <ul>
          <li v-for="(erro, index) of backendErrors" :key="index" class="red-text text-darken-2">
            <b>{{erro.field}}</b> - {{erro.message}}
          </li>
        </ul>
      </div>

      <!-- Formulário -->
      <div class="card">
        <div class="card-content">
          <span class="card-title green-text text-darken-2">
            <i class="material-icons small">{{ propriedade.id ? 'edit' : 'add_circle' }}</i>
            {{ propriedade.id ? 'Editar Propriedade' : 'Nova Propriedade Rural' }}
          </span>
          
          <form @submit.prevent="validarESalvar">
            <div class="row">
              
              <!-- Nome da Propriedade -->
              <div class="input-field col s12">
                <input 
                  type="text" 
                  id="nome" 
                  v-model="propriedade.name" 
                  :class="{ 'invalid': fieldErrors.name && fieldTouched.name }"
                  @blur="fieldTouched.name = true"
                  maxlength="255"
                  placeholder="Ex: Fazenda Esperança"
                >
                <label for="nome">Nome da Propriedade *</label>
                <span v-if="fieldErrors.name && fieldTouched.name" class="helper-text red-text">
                  {{ fieldErrors.name }}
                </span>
                <span v-else class="helper-text grey-text">
                  Nome obrigatório, máximo 255 caracteres
                </span>
              </div>

              <!-- Latitude -->
              <div class="input-field col s12 m6">
                <input 
                  type="number" 
                  id="latitude" 
                  v-model="propriedade.latitude" 
                  step="0.000001"
                  :class="{ 'invalid': fieldErrors.latitude && fieldTouched.latitude }"
                  @blur="fieldTouched.latitude = true"
                  placeholder="Ex: -15.841"
                >
                <label for="latitude">Latitude</label>
                <span v-if="fieldErrors.latitude && fieldTouched.latitude" class="helper-text red-text">
                  {{ fieldErrors.latitude }}
                </span>
                <span v-else class="helper-text grey-text">
                  Entre -90 e 90 graus (opcional)
                </span>
              </div>

              <!-- Longitude -->
              <div class="input-field col s12 m6">
                <input 
                  type="number" 
                  id="longitude" 
                  v-model="propriedade.longitude" 
                  step="0.000001"
                  :class="{ 'invalid': fieldErrors.longitude && fieldTouched.longitude }"
                  @blur="fieldTouched.longitude = true"
                  placeholder="Ex: -47.924"
                >
                <label for="longitude">Longitude</label>
                <span v-if="fieldErrors.longitude && fieldTouched.longitude" class="helper-text red-text">
                  {{ fieldErrors.longitude }}
                </span>
                <span v-else class="helper-text grey-text">
                  Entre -180 e 180 graus (opcional)
                </span>
              </div>

              <!-- Área em Hectares -->
              <div class="input-field col s12">
                <input 
                  type="number" 
                  id="area" 
                  v-model="propriedade.areaHectares" 
                  step="0.01"
                  min="0.01"
                  :class="{ 'invalid': fieldErrors.areaHectares && fieldTouched.areaHectares }"
                  @blur="fieldTouched.areaHectares = true"
                  placeholder="Ex: 120.5"
                >
                <label for="area">Área (Hectares) *</label>
                <span v-if="fieldErrors.areaHectares && fieldTouched.areaHectares" class="helper-text red-text">
                  {{ fieldErrors.areaHectares }}
                </span>
                <span v-else class="helper-text grey-text">
                  Valor positivo obrigatório
                </span>
              </div>

            </div>

            <!-- Botões -->
            <div class="form-actions">
              <button type="submit" class="waves-effect waves-light btn green darken-1">
                <i class="material-icons left">{{ propriedade.id ? 'update' : 'save' }}</i>
                {{ propriedade.id ? 'Atualizar' : 'Salvar' }}
              </button>

              <button 
                v-if="propriedade.id" 
                type="button"
                @click="cancelarEdicao" 
                class="waves-effect waves-light btn grey"
              >
                <i class="material-icons left">cancel</i>
                Cancelar
              </button>

              <button 
                v-if="!propriedade.id"
                type="button"
                @click="limparFormulario" 
                class="waves-effect waves-light btn-flat"
              >
                <i class="material-icons left">clear</i>
                Limpar
              </button>
            </div>
          </form>
        </div>
      </div>

      <!-- Tabela de Propriedades -->
      <div class="card">
        <div class="card-content">
          <span class="card-title green-text text-darken-2">
            <i class="material-icons small">list</i>
            Propriedades Rurais Cadastradas
            <span class="badge green white-text">{{ propriedades.length }}</span>
          </span>
          
          <table class="striped highlight">
            <thead>
              <tr>
                <th class="green darken-2 white-text">NOME</th>
                <th class="green darken-2 white-text">LATITUDE</th>
                <th class="green darken-2 white-text">LONGITUDE</th>
                <th class="green darken-2 white-text">ÁREA (ha)</th>
                <th class="green darken-2 white-text">AÇÕES</th>
              </tr>
            </thead>

            <tbody>
              <tr v-for="prop of propriedades" :key="prop.id">
                <td><strong>{{ prop.name }}</strong></td>
                <td>{{ prop.latitude ? prop.latitude.toFixed(6) : 'N/A' }}</td>
                <td>{{ prop.longitude ? prop.longitude.toFixed(6) : 'N/A' }}</td>
                <td>{{ prop.areaHectares }} ha</td>
                <td>
                  <button @click="visualizar(prop)" class="btn-small waves-effect waves-light green" title="Visualizar">
                    <i class="material-icons">visibility</i>
                  </button>
                  <button @click="editar(prop)" class="btn-small waves-effect waves-light blue" title="Editar">
                    <i class="material-icons">edit</i>
                  </button>
                  <button @click="remover(prop)" class="btn-small waves-effect waves-light red" title="Excluir">
                    <i class="material-icons">delete</i>
                  </button>
                </td>
              </tr>
              <tr v-if="propriedades.length === 0">
                <td colspan="5" class="center-align grey-text">
                  Nenhuma propriedade cadastrada
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import RuralProperties from './services/ruralProperties'

export default {
  name: 'App',
  data () {
    return {
      propriedade: {
        id: '',
        name: '',
        latitude: '',
        longitude: '',
        areaHectares: ''
      },
      propriedades: [],
      backendErrors: [],
      fieldErrors: {
        name: '',
        latitude: '',
        longitude: '',
        areaHectares: ''
      },
      fieldTouched: {
        name: false,
        latitude: false,
        longitude: false,
        areaHectares: false
      }
    }
  },

  mounted(){
    this.listar()
  },

  methods:{
    
    listar(){
      RuralProperties.listar().then(resposta => {
        this.propriedades = resposta.data
      }).catch(e => {
        console.log('Erro ao carregar propriedades:', e)
      })
    },

    validarFormulario() {
      let isValid = true

      if (!this.propriedade.name || this.propriedade.name.trim() === '') {
        this.fieldErrors.name = 'Nome da propriedade é obrigatório'
        isValid = false
      } else if (this.propriedade.name.length > 255) {
        this.fieldErrors.name = 'Nome não pode exceder 255 caracteres'
        isValid = false
      } else {
        this.fieldErrors.name = ''
      }

      if (this.propriedade.latitude !== '' && this.propriedade.latitude !== null) {
        const lat = parseFloat(this.propriedade.latitude)
        if (isNaN(lat) || lat < -90 || lat > 90) {
          this.fieldErrors.latitude = 'Latitude deve estar entre -90 e 90'
          isValid = false
        } else {
          this.fieldErrors.latitude = ''
        }
      } else {
        this.fieldErrors.latitude = ''
      }

      if (this.propriedade.longitude !== '' && this.propriedade.longitude !== null) {
        const lng = parseFloat(this.propriedade.longitude)
        if (isNaN(lng) || lng < -180 || lng > 180) {
          this.fieldErrors.longitude = 'Longitude deve estar entre -180 e 180'
          isValid = false
        } else {
          this.fieldErrors.longitude = ''
        }
      } else {
        this.fieldErrors.longitude = ''
      }

      if (!this.propriedade.areaHectares || this.propriedade.areaHectares === '') {
        this.fieldErrors.areaHectares = 'Área em hectares é obrigatória'
        isValid = false
      } else {
        const area = parseFloat(this.propriedade.areaHectares)
        if (isNaN(area) || area <= 0) {
          this.fieldErrors.areaHectares = 'Área deve ser um valor positivo'
          isValid = false
        } else {
          this.fieldErrors.areaHectares = ''
        }
      }

      return isValid
    },

    validarESalvar(){
      this.fieldTouched = {
        name: true,
        latitude: true,
        longitude: true,
        areaHectares: true
      }

      if (!this.validarFormulario()) {
        return
      }

      this.salvar()
    },

    salvar(){
      const propertyData = {
        name: this.propriedade.name,
        latitude: this.propriedade.latitude ? parseFloat(this.propriedade.latitude) : null,
        longitude: this.propriedade.longitude ? parseFloat(this.propriedade.longitude) : null,
        areaHectares: this.propriedade.areaHectares ? parseFloat(this.propriedade.areaHectares) : null
      }

      if(!this.propriedade.id){
        RuralProperties.salvar(propertyData).then(() => {
          this.limparFormulario()
          this.limparErros()
          alert('🌱 Propriedade cadastrada com sucesso!')
          this.listar()
        }).catch(e => {
          this.tratarErroBackend(e)
        })
      }else{
        propertyData.id = this.propriedade.id
        RuralProperties.atualizar(propertyData).then(() => {
          this.limparFormulario()
          this.limparErros()
          alert('✏️ Propriedade atualizada com sucesso!')
          this.listar()
        }).catch(e => {
          this.tratarErroBackend(e)
        })
      }
    },

    tratarErroBackend(error) {
      console.log('Erro do backend:', error)
      
      if (error.response && error.response.data) {
        const errorData = error.response.data
        
        if (error.response.status === 400) {
          this.backendErrors = []
          
          if (errorData.errors) {
            Object.keys(errorData.errors).forEach(campo => {
              this.backendErrors.push({
                field: campo,
                message: errorData.errors[campo]
              })
            })
          } else if (errorData.message) {
            this.backendErrors.push({
              field: 'Erro',
              message: errorData.message
            })
          }
        } else {
          this.backendErrors = [{
            field: 'Erro',
            message: errorData.message || 'Erro no servidor'
          }]
        }
      } else {
        this.backendErrors = [{
          field: 'Conexão',
          message: 'Erro de conexão com o servidor'
        }]
      }
    },

    visualizar(propriedade){
      alert(`🌾 Detalhes da Propriedade:\n\n🏷️ Nome: ${propriedade.name}\n📍 Latitude: ${propriedade.latitude || 'N/A'}\n📍 Longitude: ${propriedade.longitude || 'N/A'}\n📏 Área: ${propriedade.areaHectares} hectares`)
    },

    editar(propriedade){
      this.propriedade = { ...propriedade }
      this.limparErros()
     this.fieldTouched = {
        name: false,
        latitude: false,
        longitude: false,
        areaHectares: false
      }
      document.querySelector('.card-content').scrollIntoView({ 
        behavior: 'smooth' 
      })
    },

    remover(propriedade){
      if(confirm(`Tem certeza que deseja excluir a propriedade "${propriedade.name}"?`)){
        RuralProperties.apagar(propriedade).then(() => {
          this.listar()
          this.limparErros()
          alert('✅ Propriedade excluída com sucesso!')
        }).catch(e => {
          this.tratarErroBackend(e)
        })
      }
    },

    cancelarEdicao(){
      this.limparFormulario()
      this.limparErros()
    },

    limparFormulario(){
      this.propriedade = {
        id: '',
        name: '',
        latitude: '',
        longitude: '',
        areaHectares: ''
      }
      this.limparErros()
    },

    limparErros(){
      this.backendErrors = []
      this.fieldErrors = {
        name: '',
        latitude: '',
        longitude: '',
        areaHectares: ''
      }
      this.fieldTouched = {
        name: false,
        latitude: false,
        longitude: false,
        areaHectares: false
      }
    }
  }
}
</script>

<style>
.container {
  margin-top: 20px;
}

nav .brand-logo {
  display: flex;
  align-items: center;
  font-weight: 500;
}

.card {
  border-radius: 8px;
  border-top: 4px solid #2e7d32;
}

.card .card-title {
  font-weight: 500;
  display: flex;
  align-items: center;
}

.card .card-title i {
  margin-right: 10px;
}

.badge {
  margin-left: 10px;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8rem;
}

table {
  border-radius: 4px;
  overflow: hidden;
  border: 1px solid #e0e0e0;
}

th {
  font-weight: 500;
}

.btn-small {
  margin: 2px;
}

.input-field input.invalid {
  border-bottom: 2px solid #f44336 !important;
  box-shadow: 0 1px 0 0 #f44336 !important;
}

.input-field input.valid {
  border-bottom: 2px solid #4caf50 !important;
  box-shadow: 0 1px 0 0 #4caf50 !important;
}

.helper-text {
  font-size: 0.8rem;
}

.helper-text.red-text {
  font-weight: bold;
}

.helper-text.grey-text {
  color: #9e9e9e !important;
}

label[for="nome"]::after,
label[for="area"]::after {
  content: " *";
  color: #f44336;
}

.form-actions {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  margin-top: 20px;
}

tbody tr:hover {
  background-color: #f1f8e9 !important;
}


input::placeholder {
  color: #bdbdbd;
  font-style: italic;
}
</style>