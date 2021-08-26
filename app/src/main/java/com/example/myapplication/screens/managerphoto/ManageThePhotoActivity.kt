package com.example.myapplication.screens.managerphoto

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityManageThePhotoBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_manage_the_photo.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.webkit.MimeTypeMap

import android.widget.Toast
import androidx.core.view.isVisible

import com.example.myapplication.model.Upload
import com.google.firebase.storage.StorageTask


class ManageThePhotoActivity : BaseActivity<ActivityManageThePhotoBinding>(), View.OnClickListener {

    private lateinit var mImageUri: Uri
    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null


    override fun initLayout(): Int = R.layout.activity_manage_the_photo

    override fun init() {
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
    }

    override fun setOnClickForViews() {
        binding?.tvChooseFile?.setOnClickListener(this)
        binding?.tvUpFile?.setOnClickListener(this)
    }

    override fun initViews() {
        binding!!.progressBar.isVisible = false
        binding!!.tvLoading.isVisible = false
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvChooseFile -> {
                openFileChooser()
            }
            R.id.tvUpFile -> {
                upLoadFile()
            }

        }
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun upLoadFile() {
        if (mImageUri != null) {
            var imageRef = FirebaseStorage.getInstance().reference.child("image/background.png")
            imageRef.putFile(mImageUri).addOnSuccessListener {
                onBackPressed()
                Toast.makeText(this, "Cập nhật ảnh thành công", Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(this, "Cập nhật ảnh thất bại", Toast.LENGTH_SHORT).show()
            }.addOnProgressListener { p0 ->
                binding!!.progressBar.isVisible = true
                binding!!.tvLoading.isVisible = true

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            mImageUri = data.data!!
            Glide.with(this).load(mImageUri).into(imgContent)
        }
    }


}