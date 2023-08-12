<template>
  <v-container fluid :grid-list-md="!$vuetify.breakpoint.xs">
    <v-layout wrap row>
      <v-flex xs12 sm6 md3 class="pb-2"
              v-for="item in stockList"
              :key="item.stockId">
        <v-card>
          <v-container pa-1>
            <v-layout row>
              <v-flex xs7>
                <v-card-title primary-title>
                  <div>
                    <h3 class="headline">{{ item.stockName }}</h3>
                  </div>
                </v-card-title>
              </v-flex>
              <v-flex xs5>
                <v-card-title primary-title>
                  <v-avatar>
                    <v-icon medium color="orange darken-1">person</v-icon>
                    <div>{{item.peopleCnt}}</div>
                  </v-avatar>
                </v-card-title>
              </v-flex>
            </v-layout>
            <v-divider light></v-divider>
            <v-card-actions class="pa-2">
              <v-icon small color="primary">update</v-icon>
              <span class="grey--text caption font-italic">&nbsp;5분전</span>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-container>
        </v-card>
      </v-flex>

      <v-flex xs12 sm6 class="pb-2" v-for="i in 2">
        <v-card>
          <v-toolbar color="orange" flat>
            <v-toolbar-title>최근 게시물</v-toolbar-title>
            <v-spacer></v-spacer>

            <v-btn icon>
              <v-icon>search</v-icon>
            </v-btn>
          </v-toolbar>
          <v-list two-line>
            <template v-for="(item, index) in items">
              <v-subheader
                  v-if="item.header"
                  :key="item.header"
              >
                {{ item.header }}
              </v-subheader>

              <v-divider
                  v-else-if="item.divider"
                  :inset="item.inset"
                  :key="index"
              ></v-divider>

              <v-list-tile
                  v-else
                  :key="item.title"
                  avatar
                  @click=""
              >
                <v-list-tile-avatar>
                  <img :src="item.avatar">
                </v-list-tile-avatar>

                <v-list-tile-content>
                  <v-list-tile-title v-html="item.title"></v-list-tile-title>
                  <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
            </template>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>

  </v-container>
</template>
<script>
export default {
  data () {
    return {
      stockList: [
        {
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
        {
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
        {
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
        {
          stockId : '005930',
          stockName : '삼성전자',
          orderQuantity: 0.4,
          peopleCnt : 3,
        },
      ],
      progress: 33,
      items: [
        { header: 'Today' },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
          subtitle: "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
        },
        { divider: true, inset: true },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
          subtitle: "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend."
        },
      ]
    }
  },
}

</script>
<style lang="">

</style>