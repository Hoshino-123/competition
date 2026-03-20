import request from '@/utils/request';
// list
export const zhidaojiaoshiList = (params) => {
    return request({
        url: '/zhidaojiaoshi/page',
        method: 'post',
        data: params,
    })
};
export const zhidaojiaoshiDetail = (id) => {
    return request({
        url: '/zhidaojiaoshi/'+id,
        method: 'get',
    })
};
// add
export const zhidaojiaoshiSave = (params) => {
    return request({
        url: '/zhidaojiaoshi',
        method: 'post',
        data: params,
    })
};
// updt
export const zhidaojiaoshiEdit = (params) => {
    return request({
        url: '/zhidaojiaoshi',
        method: 'put',
        data: params,
    })
};
// delete
export const zhidaojiaoshiDelete = (id) => {
    return request({
        url: '/zhidaojiaoshi/'+id,
        method: 'delete',
    })
};
// pldel
export const zhidaojiaoshiDeleteList = (zhidaojiaoshis) => {
    return request({
        url: '/zhidaojiaoshi/deleteList',
        data:{list:zhidaojiaoshis},
        method: 'post',
    })
};
// all
export const zhidaojiaoshiAllList = () => {
    return request({
        url: '/zhidaojiaoshi',
        method: 'get',
    })
};
export const zhidaojiaoshiUpdatePassword = (params) =>{ return request({ url: `/zhidaojiaoshi/updatePassword`, method: 'put',data: {...params,mima:params.password}, })};
