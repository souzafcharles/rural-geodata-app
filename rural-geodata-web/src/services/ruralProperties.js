import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

class RuralProperties {
  
  // Listar todas as propriedades
  listar() {
    return api.get('/properties')
  }

  // Salvar nova propriedade
  salvar(property) {
    return api.post('/properties', property)
  }

  // Atualizar propriedade
  atualizar(property) {
    return api.put(`/properties/${property.id}`, property)
  }

  // Deletar propriedade
  apagar(property) {
    return api.delete(`/properties/${property.id}`)
  }
}

export default new RuralProperties()