<template>
  <footer class="section has-background-dark">
    <div class="container">
      <div class="content has-text-centered">
        <p class="footer-copyright has-text-light">
          <small>Copyright © {{ getCurrentYear() }} "Masato Koishi" All Rights Reserved.</small>
        </p>
        <div class="made-by-bulma">
          <a href="https://bulma.io">
            <img src="https://bulma.io/images/made-with-bulma--white.png" alt="Made with Bulma" width="163" height="31">
          </a>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  methods: {
    getCurrentYear () {
      return (new Date()).getFullYear()
    }
  }
}
</script>

