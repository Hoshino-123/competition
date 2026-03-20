import request from '@/utils/request';
// list
export const tuanduifuzerenList = (params) => {
    return request({
        url: '/tuanduifuzeren/page',
        method: 'post',
        data: params,
    })
};
export const tuanduifuzerenDetail = (id) => {
    return request({
        url: '/tuanduifuzeren/'+id,
        method: 'get',
    })
};
// add
export const tuanduifuzerenSave = (params) => {
    return request({
        url: '/tuanduifuzeren',
        method: 'post',
        data: params,
    })
};
// updt
export const tuanduifuzerenEdit = (params) => {
    return request({
        url: '/tuanduifuzeren',
        method: 'put',
        data: params,
    })
};
// delete
export const tuanduifuzerenDelete = (id) => {
    return request({
        url: '/tuanduifuzeren/'+id,
        method: 'delete',
    })
};
// pldel
export const tuanduifuzerenDeleteList = (tuanduifuzerens) => {
    return request({
        url: '/tuanduifuzeren/deleteList',
        data:{list:tuanduifuzerens},
        method: 'post',
    })
};
// all
export const tuanduifuzerenAllList = () => {
    return request({
        url: '/tuanduifuzeren',
        method: 'get',
    })
};
export const tuanduifuzerenUpdatePassword = (params) =>{ return request({ url: `/tuanduifuzeren/updatePassword`, method: 'put',data: {...params,mima:params.password}, })};
