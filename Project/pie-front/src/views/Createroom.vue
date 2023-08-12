<template>
    <v-card>
        <v-card-title>
          <span class="text-h5">소수점 매매</span>
        </v-card-title>
        <v-container>
            <v-autocomplete
                v-model = "selected"
                :items="['LG생활건강', '삼성바이오로직스', '에코프로']"
                label="종목"
                multiple
            ></v-autocomplete>
        </v-container>
        <v-card-text v-for="name in selected">
          <v-container>
            <v-row>
              <v-col cols="12">
                {{ name }}
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-card height="70%" hint="시장가">가격</v-card>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="최소 가격(%)"
                  hint="시장가 대비 -(입력)%"
                  required
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="최대 가격(%)"
                  hint="시장가 대비 +(입력)%"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="매수 수량"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="dialog = false"
          >
            주문
          </v-btn>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="dialog = false"
          >
            나가기
          </v-btn>
        </v-card-actions>
      </v-card>
</template>

<script>
export default{
    data () {
        return {
            selected: [],
        }
    }
}
</script>