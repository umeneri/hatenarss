<template>
  <div @click="onClick"
    class="button is-primary"
    :class="{ 'is-loading': isLoading }">
    {{ title }}
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String
    },
    loading: {
      type: Boolean
    },
    onClickCallback: {
      type: Function
    }
  },
  data () {
    return {
      isLoading: this.loading
    }
  },
  mounted () {
  },
  methods: {
    startLoading () {
      this.isLoading = true
    },
    endLoading () {
      this.isLoading = false
    },
    async onClick() {
      this.startLoading()
      await this.onClickCallback()
      this.endLoading()
    }
  }
}
</script>
