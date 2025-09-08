Page({
  //页面的初始数据
  data: {
    goodsTypeArr:[],
    goodsType:{},
    goodsArr:[]
  },

  selectType(event){
      let index = event.target.dataset.index
      this.setData({
          goodsType:this.data.goodsTypeArr[index]
      })
      //发送请求，获得商品数据
      this.selectGoods()
  },

  selectGoods(){
      wx.request({
          //模板字符串
        url: `http://127.0.0.1:3000/goods/${this.data.goodsType.goodsTypeId}`,
        method:'GET',
        success:res=>{
            this.setData({
                goodsArr:res.data
            })
        }
      })
  },

  toGoods(event){
      let index = event.mark.index
      //将点击选中的商品信息存储到小程序的storage中
      wx.setStorageSync('goods', this.data.goodsArr[index])
      //跳转到商品页面
      wx.navigateTo({
        url: '/pages/goods/goods',
      })
  },

  //生命周期函数--监听页面加载
  onLoad: function (options) {
    
  },

  //生命周期函数--监听页面初次渲染完成
  onReady: function () {
    
  },

  //生命周期函数--监听页面显示
  onShow: function () {
    wx.request({
      url: 'http://127.0.0.1:3000/goodsType',
      method:'get',
      success:(res)=>{
        this.setData({
            goodsTypeArr:res.data,
            goodsType:res.data[0]
        })
        //发送请求，获得商品数据
        this.selectGoods()
      }
    })
  },

  //生命周期函数--监听页面隐藏
  onHide: function () {
    
  },

  //生命周期函数--监听页面卸载
  onUnload: function () {
    
  },

  //页面相关事件处理函数--监听用户下拉动作
  onPullDownRefresh: function () {
    
  },

  //页面上拉触底事件的处理函数
  onReachBottom: function () {
    
  },

  //用户点击右上角分享
  onShareAppMessage: function () {
    
  }
})