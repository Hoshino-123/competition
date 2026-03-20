import request from '@/utils/request';
// list
export const daxueshengList = (params) => {
    return request({
        url: '/daxuesheng/page',
        method: 'post',
        data: params,
    })
};
export const daxueshengDetail = (id) => {
    return request({
        url: '/daxuesheng/'+id,
        method: 'get',
    })
};
// add
export const daxueshengSave = (params) => {
    return request({
        url: '/daxuesheng',
        method: 'post',
        data: params,
    })
};
// updt
export const daxueshengEdit = (params) => {
    return request({
        url: '/daxuesheng',
        method: 'put',
        data: params,
    })
};
// delete
export const daxueshengDelete = (id) => {
    return request({
        url: '/daxuesheng/'+id,
        method: 'delete',
    })
};
// pldel
export const daxueshengDeleteList = (daxueshengs) => {
    return request({
        url: '/daxuesheng/deleteList',
        data:{list:daxueshengs},
        method: 'post',
    })
};
// all
export const daxueshengAllList = () => {
    return request({
        url: '/daxuesheng',
        method: 'get',
    })
};
export const daxueshengUpdatePassword = (params) =>{ return request({ url: `/daxuesheng/updatePassword`, method: 'put',data: {...params,mima:params.password}, })};
